public class KonfirmasiPesanan {
    public static double[] tampilkan(
        String[] pesananMakanan, int[] kuantitasMakanan, int jumlahMakanan,
        String[] pesananMinuman, int[] kuantitasMinuman, int jumlahMinuman
    ) {
        double totalMinuman = 0;
        double totalMakanan = 0;
        double totalPajakMinuman = 0;
        double totalPajakMakanan = 0;

        System.out.println("\nKonfirmasi Pesanan:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-40s %-20s %-30s %-15s\n", "Kode", "Nama", "Kuantitas", "Harga", "Pajak");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < jumlahMinuman; i++) {
            String kode = pesananMinuman[i];
            int kuantitas = kuantitasMinuman[i];
            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = minuman.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, true);
                    double hargaTotal = hargaSatuan * kuantitas;
                    double pajakTotal = pajakSatuan * kuantitas;

                    totalMinuman += hargaTotal;
                    totalPajakMinuman += pajakTotal;

                    System.out.printf("%-10s %-40s %-20d %-30.2f %-15.2f\n",
                        kode.toUpperCase(), minuman.getNama(), kuantitas, hargaTotal, pajakTotal);
                }
            }
        }

        for (int i = 0; i < jumlahMakanan; i++) {
            String kode = pesananMakanan[i];
            int kuantitas = kuantitasMakanan[i];
            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = makanan.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, false);
                    double hargaTotal = hargaSatuan * kuantitas;
                    double pajakTotal = pajakSatuan * kuantitas;

                    totalMakanan += hargaTotal;
                    totalPajakMakanan += pajakTotal;

                    System.out.printf("%-10s %-40s %-20d %-30.2f %-15.2f\n",
                        kode.toUpperCase(), makanan.getNama(), kuantitas, hargaTotal, pajakTotal);
                }
            }
        }

        double totalSebelumPajak = totalMakanan + totalMinuman;
        double totalPajak = totalPajakMinuman + totalPajakMakanan;
        double totalSetelahPajak = totalSebelumPajak + totalPajak;

        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("Total Sebelum Pajak    : %.2f IDR\n", totalSebelumPajak);
        System.out.printf("Total Pajak            : %.2f IDR\n", totalPajak);
        System.out.printf("Total Setelah Pajak    : %.2f IDR\n", totalSetelahPajak);

        return new double[] { totalMakanan, totalMinuman, totalSetelahPajak };
    }
}
