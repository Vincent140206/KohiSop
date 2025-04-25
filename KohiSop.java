import java.util.*;

public class KohiSop {
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_KUANTITAS_MINUMAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pesananMinuman = new String[MAX_MINUMAN];
        int[] kuantitasMinuman = new int[MAX_MINUMAN];
        String[] pesananMakanan = new String[MAX_MAKANAN];
        int[] kuantitasMakanan = new int[MAX_MAKANAN];

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();

        int pesanan_makanan = InputPesanan.prosesPesanan(
            scanner,
            pesananMakanan,
            kuantitasMakanan,
            MAX_MAKANAN,
            MAX_MAKANAN,
            "Makanan",
            Makanan.getDaftarMakanan()
        );
        if (pesanan_makanan == -1) return;

        int pesanan_minuman = InputPesanan.prosesPesanan(
            scanner,
            pesananMinuman,
            kuantitasMinuman,
            MAX_MINUMAN,
            MAX_KUANTITAS_MINUMAN,
            "Minuman",
            Minuman.getDaftarMinuman()
        );
        if (pesanan_minuman == -1) return;

        S.clear();
        double[] hasil = KonfirmasiPesanan.tampilkan(
            pesananMakanan, kuantitasMakanan, pesanan_makanan,
            pesananMinuman, kuantitasMinuman, pesanan_minuman
        );

        double totalMakanan = hasil[0];
        double totalMinuman = hasil[1];
        double totalSebelumPajak = totalMakanan + totalMinuman;
        double totalSetelahPajak = hasil[2];

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

        if (payment == null || !payment.cekSaldo(totalSetelahPajak)) {
            System.out.println("Saldo tidak cukup untuk melakukan pembayaran atau metode pembayaran tidak valid.");
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
        Kuitansi.tampilkan(
            pesananMakanan, kuantitasMakanan, pesanan_makanan,
            pesananMinuman, kuantitasMinuman, pesanan_minuman,
            totalSebelumPajak, totalSetelahPajak, totalConvert, mataUang, payment
        );
    }

    public static double hitungPajak(double harga, boolean isMinuman) {
        if (isMinuman) {
            if (harga < 50000) return 0;
            else if (harga <= 55000) return harga * 0.08;
            else return harga * 0.11;
        } else {
            return harga > 50000 ? harga * 0.08 : harga * 0.11;
        }
    }

    public static String potong(String nama) {
        return nama.length() > 20 ? nama.substring(0, 17) + "..." : nama;
    }
}