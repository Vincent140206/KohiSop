import java.util.*;

public class KohiSop {
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_KUANTITAS_MINUMAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pesananMinuman = new String[MAX_MINUMAN];
        int[] kuantitasMinuman = new int[MAX_MINUMAN];
        String[] pesananMakanan = new String[MAX_MAKANAN];
        int[] kuantitasMakanan = new int[MAX_MAKANAN];

        int pesanan_minuman = 0;
        int pesanan_makanan = 0;

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();

        while (pesanan_makanan < MAX_MAKANAN) {
            S.clear();
            Makanan.tampilkanDaftarMakanan();
            System.out.println("Masukkan kode makanan (atau 'CC' untuk membatalkan)");
            System.out.print("Jika sudah selesai, masukan 'Selesai': ");
            String kodeMakanan = scanner.nextLine().trim();

            if (kodeMakanan.equalsIgnoreCase("CC")) {
                System.out.println("Pesanan dibatalkan.");
                return;
            }

            if (kodeMakanan.equalsIgnoreCase("selesai")) {
                System.out.println("Anda telah selesai memilih makanan.");
                break;
            }

            if (kodeMakanan.equalsIgnoreCase("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                break;
            }

            boolean valid = false;
            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kodeMakanan)) {
                    valid = true;
                    pesananMakanan[pesanan_makanan] = kodeMakanan;
                    break;
                }
            }

            if (!valid) {
                S.move(0, -70);
                System.out.println("Kode makanan tidak valid. Silakan coba lagi.");
                S.delay(2000);
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk batal, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            int kuantitas = 1;

            if (inputKuantitas.equals("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                continue;
            }

            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            } else if (!inputKuantitas.isEmpty()) {
                try {
                    kuantitas = Integer.parseInt(inputKuantitas);
                    if (kuantitas < 0 || kuantitas > MAX_MAKANAN) {
                        System.out.println("Jumlah tidak valid. Harus antara 0 dan " + MAX_MAKANAN + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    continue;
                }
            }

            kuantitasMakanan[pesanan_makanan] = kuantitas;
            pesanan_makanan++;
        }

        while (pesanan_minuman < MAX_MINUMAN) {
            S.clear();
            Minuman.tampilkanDaftarMinuman();
            System.out.println("Masukkan kode minuman (atau 'CC' untuk membatalkan)");
            System.out.print("Jika sudah selesai, masukan 'Selesai': ");
            String kodeMinuman = scanner.nextLine().trim();

            if (kodeMinuman.equalsIgnoreCase("CC")) {
                System.out.println("Pesanan dibatalkan.");
                return;
            }

            if (kodeMinuman.equalsIgnoreCase("selesai")) {
                System.out.println("Anda telah selesai memilih minuman.");
                break;
            }

            if (kodeMinuman.equalsIgnoreCase("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                break;
            }

            boolean valid = false;
            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kodeMinuman)) {
                    valid = true;
                    pesananMinuman[pesanan_minuman] = kodeMinuman;
                    break;
                }
            }

            if (!valid) {
                S.move(0, -70);
                System.out.println("Kode minuman tidak valid. Silakan coba lagi.");
                S.delay(2000);
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk batal, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            int kuantitas = 1;

            if (inputKuantitas.equals("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                continue;
            }

            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            } else if (!inputKuantitas.isEmpty()) {
                try {
                    kuantitas = Integer.parseInt(inputKuantitas);
                    if (kuantitas < 0 || kuantitas > MAX_KUANTITAS_MINUMAN) {
                        System.out.println("Jumlah tidak valid. Harus antara 0 dan " + MAX_KUANTITAS_MINUMAN + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak sesuai. Harap masukan angka");
                    continue;
                }
            }

            kuantitasMinuman[pesanan_minuman] = kuantitas;
            pesanan_minuman++;
        }

        S.clear();
        double totalMinuman = 0;
        double totalMakanan = 0;
        double totalPajakMinuman = 0;
        double totalPajakMakanan = 0;

        System.out.println("\nKonfirmasi Pesanan:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-40s %-20s %-30s %-15s\n", "Kode", "Nama", "Kuantitas", "Harga", "Pajak");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < pesanan_minuman; i++) {
            String kode = pesananMinuman[i];
            int kuantitas = kuantitasMinuman[i];
            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kode)) {
                    double harga = minuman.getHarga() * kuantitas;
                    double pajak = hitungPajak(minuman.getHarga(), true);
                    totalMinuman += harga;
                    totalPajakMinuman += pajak;
                    System.out.printf("%-10s %-40s %-20d %-30.2f %-15.2f\n", kode.toUpperCase(), minuman.getNama(), kuantitas, harga, pajak);
                }
            }
        }

        for (int i = 0; i < pesanan_makanan; i++) {
            String kode = pesananMakanan[i];
            int kuantitas = kuantitasMakanan[i];
            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double harga = makanan.getHarga() * kuantitas;
                    double pajak = hitungPajak(makanan.getHarga(), false);
                    totalMakanan += harga;
                    totalPajakMakanan += pajak;
                    System.out.printf("%-10s %-40s %-20d %-30.2f %-15.2f\n", kode.toUpperCase(), makanan.getNama(), kuantitas, harga, pajak);
                }
            }
        }

        double totalSebelumPajak = totalMinuman + totalMakanan;
        double totalPajak = totalPajakMinuman + totalPajakMakanan;
        double totalSetelahPajak = totalSebelumPajak + totalPajak;

        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("Total Sebelum Pajak    : %.2f IDR\n", totalSebelumPajak);
        System.out.printf("Total Pajak            : %.2f IDR\n", totalPajak);
        System.out.printf("Total Setelah Pajak    : %.2f IDR\n", totalSetelahPajak);

        IPayment payment = null;
        while (true) {
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
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak sesuai. Harap masukkan angka.");
            }
        }

        if (!payment.cekSaldo(totalSetelahPajak)) {
            System.out.println("Saldo tidak cukup untuk melakukan pembayaran.");
            return;
        }

        double totalBayar = payment.hitungTotal(totalSetelahPajak);
        System.out.printf("\nTotal yang harus dibayar            : %.2f IDR\n", totalBayar);
        System.out.print("Pilih mata uang (USD, JPY, MYR, EUR): ");

        String mataUang = scanner.nextLine().trim().toUpperCase();
        while (!MataUang.cekValid(mataUang)) {
            System.out.print("Mata uang tidak valid. Silakan pilih mata uang (USD, JPY, MYR, EUR): ");
            mataUang = scanner.nextLine().trim().toUpperCase();
        }

        double totalConvert = MataUang.konversi(totalBayar, mataUang);
        String simbol = MataUang.getSimbolMataUang(mataUang);
        System.out.printf("Total dalam %s: %s%,.2f\n", mataUang, simbol, totalConvert);

        S.clear();
        System.out.println("================= KUITANSI PEMBAYARAN =================");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-6s %-20s %-6s %-10s %-8s\n", "Kode", "Nama", "Qty", "Harga", "Pajak");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < pesanan_makanan; i++) {
            String kode = pesananMakanan[i];
            int qty = kuantitasMakanan[i];

            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double harga = makanan.getHarga() * qty;
                    double pajak = hitungPajak(makanan.getHarga(), false);
                    System.out.printf("%-6s %-20s %-6d %-10.2f %-8.2f\n", kode.toUpperCase(), potong(makanan.getNama()), qty, harga, pajak);
                }
            }
        }
        

        for (int i = 0; i < pesanan_minuman; i++) {
            String kode = pesananMinuman[i];
            int qty = kuantitasMinuman[i];
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kode)) {
                    double harga = minuman.getHarga() * qty;
                    double pajak = hitungPajak(minuman.getHarga(), true);
                    System.out.printf("%-6s %-20s %-6d %-10.2f %-8.2f\n", kode.toUpperCase(), potong(minuman.getNama()), qty, harga, pajak);
                }
            }
        }

        double convertSblm = MataUang.konversi(totalSebelumPajak, mataUang);
        double admin = MataUang.konversi(payment.getBiayaAdmin(), mataUang);
        double diskon = payment.getDiskon() * 100;

        System.out.println("-------------------------------------------------------");
        System.out.printf("%-41s:    %s%,.2f\n", "Total Sebelum Pajak", simbol, convertSblm);
        System.out.printf("%-41s:    %s%,.2f\n", "Total Setelah Pajak", simbol, totalConvert);
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-41s:    %s\n", "Metode Pembayaran", payment.getNamaMetode());
        System.out.printf("%-41s:    %s\n", "Mata Uang", mataUang);
        System.out.printf("%-41s:    %.0f%%\n", "Diskon Pembayaran", diskon);
        System.out.printf("%-41s:    %s\n", "Biaya Admin", mataUang);
        System.out.printf("Total dalam %s sebelum diskon dan admin :    %s%,.2f\n", mataUang, simbol, totalConvert);
        System.out.printf("Total dalam %s sesudah diskon dan admin :    %s%,.2f\n", mataUang, simbol, totalConvert + admin + (diskon / 100));
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-41s:    %s%,.2f\n", "Total yang harus dibayar", simbol, totalConvert + admin + (diskon / 100));
        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih dan silakan datang kembali!");
    }

    private static double hitungPajak(double harga, boolean isMinuman) {
        if (isMinuman) {
            if (harga < 50) return 0;
            else if (harga <= 55) return harga * 0.08;
            else return harga * 0.11;
        } else {
            return harga > 50 ? harga * 0.08 : harga * 0.11;
        }
    }

    public static String potong(String nama) {
        return nama.length() > 20 ? nama.substring(0, 17) + "..." : nama;
    }
}