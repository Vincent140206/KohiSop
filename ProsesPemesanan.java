import java.util.*;

public class ProsesPemesanan {
    
    public static boolean prosesPesanan(Scanner scanner, DataPelanggan pelanggan, DatabaseMembership db, int nomorPelanggan, int maxMakanan, int maxMinuman, int maxQtyMinuman) {
        
        pelanggan.nomorPelanggan = nomorPelanggan;
        
        int pesanan_makanan = InputPesanan.prosesPesanan(
                scanner,
                pelanggan.pesananMakanan,
                pelanggan.kuantitasMakanan,
                maxMakanan,
                maxMakanan,
                "Makanan",
                new ArrayList<Menu>(Makanan.getDaftarMakanan()));
        
        if (pesanan_makanan == -1) {
            System.out.println("Pesanan makanan dibatalkan untuk pelanggan ke-" + nomorPelanggan);
            return false;
        }

        int pesanan_minuman = InputPesanan.prosesPesanan(
                scanner,
                pelanggan.pesananMinuman,
                pelanggan.kuantitasMinuman,
                maxMinuman,
                maxQtyMinuman,
                "Minuman",
                new ArrayList<Menu>(Minuman.getDaftarMinuman()));
        
        if (pesanan_minuman == -1) {
            System.out.println("Pesanan minuman dibatalkan untuk pelanggan ke-" + nomorPelanggan);
            return false;
        }

        pelanggan.hitungTotalQty();
                
        S.clear();
        pelanggan.member = Membership.inputDanProsesMembership(scanner, db, pelanggan.totalQty);

        processPesananMakanan(pelanggan);
        processPesananMinuman(pelanggan);
        
        KohiSop.insertionSort(pelanggan.daftarItem);

        KonfirmasiPesanan.tampilkan(
                pelanggan.pesananMakanan, pelanggan.kuantitasMakanan,
                pelanggan.pesananMinuman, pelanggan.kuantitasMinuman,
                pelanggan.member);

        pelanggan.hitungTotalHarga();

        // Diskon member
        double diskonPoin = 0;
        if (pelanggan.member != null) {
            int totalPoin = pelanggan.member.getPoin();
            double nilaiPoin = totalPoin * 2000;

            if (nilaiPoin >= pelanggan.totalSetelahPajak) {
                int poinTerpakai = (int) Math.ceil(pelanggan.totalSetelahPajak / 2000.0);
                diskonPoin = poinTerpakai * 2000;
                pelanggan.member.kurangiPoin(poinTerpakai);
                pelanggan.totalSetelahPajak = 0;
            } 

            System.out.printf("Diskon dari poin: -%,.0f IDR\n", diskonPoin);
        }

        pelanggan.prosesPoin(); 
        pelanggan.poinBaru = pelanggan.member.getPoin() - (pelanggan.poinSebelum - pelanggan.poinTerpakai);


        pelanggan.payment = prosesMetodePembayaran(scanner, pelanggan.totalSetelahPajak);
        if (pelanggan.payment == null) {
            System.out.println("Pembayaran gagal untuk pelanggan ke-" + nomorPelanggan);
            return false;
        }

        pelanggan.totalBayar = pelanggan.payment.hitungTotal(pelanggan.totalSetelahPajak);
        System.out.printf("\nTotal yang harus dibayar: %.2f IDR\n", pelanggan.totalBayar);

        pelanggan.mataUang = prosesMataUang(scanner);
        pelanggan.totalConvert = MataUang.konversi(pelanggan.totalBayar, pelanggan.mataUang);
        String simbol = MataUang.getSimbolMataUang(pelanggan.mataUang);
        System.out.printf("Total dalam %s: %s%,.2f\n", pelanggan.mataUang, simbol, pelanggan.totalConvert);

        pelanggan.prosesPoin();

        System.out.println("\n" + "=".repeat(100));
        System.out.println(" ".repeat(27) + "KUITANSI PELANGGAN KE-" + nomorPelanggan);
        System.out.println("=".repeat(100));
        
        Kuitansi.tampilkan(
            pelanggan.daftarItem,
            pelanggan.totalSebelumPajak,
            pelanggan.totalSetelahPajak,
            pelanggan.totalConvert,
            pelanggan.mataUang,
            pelanggan.payment,
            pelanggan.member,
            pelanggan.poinSebelum,
            pelanggan.poinBaru,
            pelanggan.poinTerpakai
        );

        return true;
    }

    private static void processPesananMakanan(DataPelanggan pelanggan) {
        for (int i = 0; i < pelanggan.pesananMakanan.size(); i++) {
            String kode = pelanggan.pesananMakanan.get(i);
            int qty = pelanggan.kuantitasMakanan.get(i);
            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = makanan.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, false, pelanggan.member);
                    pelanggan.daftarItem.add(new KonfirmasiPesanan.ItemPesanan(
                            kode.toUpperCase(),
                            makanan.getNama(),
                            qty,
                            hargaSatuan,
                            pajakSatuan,
                            false));
                    break;
                }
            }
        }
    }

    private static void processPesananMinuman(DataPelanggan pelanggan) {
        for (int i = 0; i < pelanggan.pesananMinuman.size(); i++) {
            String kode = pelanggan.pesananMinuman.get(i);
            int qty = pelanggan.kuantitasMinuman.get(i);
            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = minuman.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, true, pelanggan.member);
                    pelanggan.daftarItem.add(new KonfirmasiPesanan.ItemPesanan(
                            kode.toUpperCase(),
                            minuman.getNama(),
                            qty,
                            hargaSatuan,
                            pajakSatuan,
                            true));
                    break;
                }
            }
        }
    }

    private static IPayment prosesMetodePembayaran(Scanner scanner, double totalSetelahPajak) {
        IPayment payment = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (attempts < maxAttempts) {
            System.out.println("\nPilih metode pembayaran: ");
            System.out.println("1. Tunai");
            System.out.println("2. QRIS");
            System.out.println("3. eMoney");
            System.out.print("Pilihan Anda: ");
            String input = scanner.nextLine().trim();

            try {
                int pilihan = Integer.parseInt(input);
                switch (pilihan) {
                    case 1 -> payment = new Tunai();
                    case 2 -> payment = new QRIS();
                    case 3 -> payment = new eMoney();
                    default -> {
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        attempts++;
                        continue;
                    }
                }
                
                if (payment != null && payment.cekSaldo(totalSetelahPajak)) {
                    return payment;
                } else {
                    System.out.println("Saldo tidak cukup untuk metode pembayaran ini. Silakan pilih metode lain.");
                    attempts++;
                    payment = null;
                    continue;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Input tidak sesuai. Harap masukkan angka.");
                attempts++;
            }
        }
        
        System.out.println("Terlalu banyak percobaan yang gagal. Pembayaran dibatalkan.");
        return null;
    }

    private static String prosesMataUang(Scanner scanner) {
        System.out.print("Pilih mata uang (USD, JPY, MYR, EUR): ");
        String mataUang = scanner.nextLine().trim().toUpperCase();
        while (!MataUang.cekValid(mataUang)) {
            System.out.print("Mata uang tidak valid. Silakan pilih mata uang (USD, JPY, MYR, EUR): ");
            mataUang = scanner.nextLine().trim().toUpperCase();
        }
        return mataUang;
    }
}