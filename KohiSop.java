
import java.util.*;

public class KohiSop { 
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_KUANTITAS_MINUMAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> pesananMinuman = new ArrayList<>();
        ArrayList<Integer> kuantitasMinuman = new ArrayList<>();
        ArrayList<String> pesananMakanan = new ArrayList<>();
        ArrayList<Integer> kuantitasMakanan = new ArrayList<>();

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();

        while (pesananMakanan.size() < MAX_MAKANAN) {
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
            for (Makanan makanan : Makanan.daftarMakanan) {
                if (makanan.getKode().equalsIgnoreCase(kodeMakanan)) {
                    valid = true;
                    pesananMakanan.add(kodeMakanan);
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

            kuantitasMakanan.add(kuantitas);
        }

        while (pesananMinuman.size() < MAX_MINUMAN) {
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

            boolean valid = false;
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kodeMinuman)) {
                    valid = true;
                    pesananMinuman.add(kodeMinuman);
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

            kuantitasMinuman.add(kuantitas);
        }

        S.clear();
        double totalMinuman = 0;
        double totalMakanan = 0;
        double totalPajakMinuman = 0;
        double totalPajakMakanan = 0;

        System.out.println("\nKuitansi Pesanan:");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s %-10s %-10s\n", "Kode", "Nama", "Kuantitas", "Harga", "Pajak");
        
        for (int i = 0; i < pesananMinuman.size(); i++) {
            String kode = pesananMinuman.get(i);
            int kuantitas = kuantitasMinuman.get(i);
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kode)) {
                    double harga = minuman.getHarga() * kuantitas;
                    double pajak = hitungPajak(minuman.getHarga(), true);
                    totalMinuman += harga;
                    totalPajakMinuman += pajak;
                    System.out.printf("%-10s %-30s %-10d %-10.2f %-10.2f\n", kode.toUpperCase(), minuman.getNama(), kuantitas, harga, pajak);
                }
            }
        }

        for (int i = 0; i < pesananMakanan.size(); i++) {
            String kode = pesananMakanan.get(i);
            int kuantitas = kuantitasMakanan.get(i);
            for (Makanan makanan : Makanan.daftarMakanan) {
                if (makanan.getKode().equalsIgnoreCase(kode)) {
                    double harga = makanan.getHarga() * kuantitas;
                    double pajak = hitungPajak(makanan.getHarga(), false);
                    totalMakanan += harga;
                    totalPajakMakanan += pajak;
                    System.out.printf("%-10s %-30s %-10d %-10.2f %-10.2f\n", kode.toUpperCase(), makanan.getNama(), kuantitas, harga, pajak);
                }
            }
        }
        double totalSebelumPajak = totalMinuman + totalMakanan;
        double totalPajak = totalPajakMinuman + totalPajakMakanan;
        double totalSetelahPajak = totalSebelumPajak + totalPajak;

        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("Total Sebelum Pajak: %.2f IDR\n", totalSebelumPajak);
        System.out.printf("Total Pajak: %.2f IDR\n", totalPajak);
        System.out.printf("Total Setelah Pajak: %.2f IDR\n", totalSetelahPajak);

        Payment payment = null;
        while (true) {
            System.out.print("Pilih metode pembayaran (1. Tunai, 2. QRIS, 3. eMoney): ");
            String input = scanner.nextLine().trim();

            try {
                int pilihan = Integer.parseInt(input); 

                switch (pilihan) {
                    case 1 -> payment = new ITunai();
                    case 2 -> payment = new IQRIS();
                    case 3 -> payment = new IeMoney();
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
        System.out.println("Total yang harus dibayar: " + totalBayar + " IDR");
        System.out.print("Pilih mata uang (USD, JPY, MYR, EUR): ");
        String mataUang = scanner.nextLine().trim().toUpperCase();

        double totalConvert = MataUang.konversiKeIDR(totalBayar, mataUang);
        System.out.println("Total dalam " + mataUang + ": " + totalConvert);
        System.out.println("Terima kasih atas pesanan Anda!");
    }

    private static double hitungPajak(double harga, boolean isMinuman) {
        if (isMinuman) {
            if (harga < 50) {
                return 0;
            } else if (harga >= 50 && harga <= 55 ) {
                return harga * 0.08; 
            } else {
                return harga * 0.11; 
            }
        } else {
            if (harga > 50) {
                return harga * 0.08; 
            } else {
                return harga * 0.11; 
            }
        }
    }
}