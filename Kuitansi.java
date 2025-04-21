public class Kuitansi {
    public static void tampilkan(
        String[] pesananMakanan, int[] kuantitasMakanan, int pesanan_makanan,
        String[] pesananMinuman, int[] kuantitasMinuman, int pesanan_minuman,
        double totalSebelumPajak, double totalSetelahPajak,
        double totalConvert, String mataUang, IPayment payment
    ) {    
        double convertSblm = MataUang.konversi(totalSebelumPajak, mataUang);
        double admin = MataUang.konversi(payment.getBiayaAdmin(), mataUang);
        double diskon = payment.getDiskon() * 100;
        String simbol = MataUang.getSimbolMataUang(mataUang);

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
                    double pajak = KohiSop.hitungPajak(makanan.getHarga(), false);
                    System.out.printf("%-6s %-20s %-6d %-10.2f %-8.2f\n", kode.toUpperCase(), KohiSop.potong(makanan.getNama()), qty, harga, pajak);
                }
            }
        }

        for (int i = 0; i < pesanan_minuman; i++) {
            String kode = pesananMinuman[i];
            int qty = kuantitasMinuman[i];
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kode)) {
                    double harga = minuman.getHarga() * qty;
                    double pajak = KohiSop.hitungPajak(minuman.getHarga(), true);
                    System.out.printf("%-6s %-20s %-6d %-10.2f %-8.2f\n", kode.toUpperCase(), KohiSop.potong(minuman.getNama()), qty, harga, pajak);
                }
            }
        }

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
        System.out.println("       Terima kasih dan silakan datang kembali!        ");
    }
}
