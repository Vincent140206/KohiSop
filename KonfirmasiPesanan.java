import java.util.ArrayList;

public class KonfirmasiPesanan {

    public static class ItemPesanan {
        private String kode;
        private String nama;
        private int kuantitas;
        private double hargaSatuan;
        private double pajakSatuan;
        private boolean isMinuman;

        public ItemPesanan(String kode, String nama, int kuantitas, double hargaSatuan, double pajakSatuan,
                boolean isMinuman) {
            this.kode = kode;
            this.nama = nama;
            this.kuantitas = kuantitas;
            this.hargaSatuan = hargaSatuan;
            this.pajakSatuan = pajakSatuan;
            this.isMinuman = isMinuman;
        }

        public double getHargaTotal() {
            return hargaSatuan * kuantitas;
        }

        public double getPajakTotal() {
            return pajakSatuan * kuantitas;
        }

        public String getKode() {
            return kode;
        }

        public String getNama() {
            return nama;
        }

        public int getKuantitas() {
            return kuantitas;
        }

        public boolean isMinuman() {
            return isMinuman;
        }

        public double getHargaSatuan() {
            return hargaSatuan;
        }

        public double getPajakSatuan() {
            return pajakSatuan;
        }

    }

    public static void insertionSort(ArrayList<ItemPesanan> daftarItem) {
        for (int i = 1; i < daftarItem.size(); i++) {
            ItemPesanan key = daftarItem.get(i);
            double keyHarga = key.getHargaTotal();
            int j = i - 1;

            while (j >= 0 && daftarItem.get(j).getHargaTotal() > keyHarga) {
                daftarItem.set(j + 1, daftarItem.get(j));
                j--;
            }
            daftarItem.set(j + 1, key);
        }
    }

    public static double[] tampilkan(
            ArrayList<String> pesananMakanan, ArrayList<Integer> kuantitasMakanan,
            ArrayList<String> pesananMinuman, ArrayList<Integer> kuantitasMinuman,
            Membership member) {

        ArrayList<ItemPesanan> daftarItem = new ArrayList<>();

        double totalMinuman = 0;
        double totalMakanan = 0;
        double totalPajakMinuman = 0;
        double totalPajakMakanan = 0;

        for (int i = 0; i < pesananMakanan.size(); i++) {
            String kode = pesananMakanan.get(i);
            int kuantitas = kuantitasMakanan.get(i);

            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = makanan.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, false, member);

                    daftarItem.add(new ItemPesanan(
                            kode.toUpperCase(), makanan.getNama(), kuantitas, hargaSatuan, pajakSatuan, false));

                    totalMakanan += hargaSatuan * kuantitas;
                    totalPajakMakanan += pajakSatuan * kuantitas;
                    break;
                }
            }
        }

        for (int i = 0; i < pesananMinuman.size(); i++) {
            String kode = pesananMinuman.get(i);
            int kuantitas = kuantitasMinuman.get(i);

            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = minuman.getHarga();
                    double pajakSatuan = KohiSop.hitungPajak(hargaSatuan, true, member);

                    daftarItem.add(new ItemPesanan(
                            kode.toUpperCase(), minuman.getNama(), kuantitas, hargaSatuan, pajakSatuan, true));

                    totalMinuman += hargaSatuan * kuantitas;
                    totalPajakMinuman += pajakSatuan * kuantitas;
                    break;
                }
            }
        }

        insertionSort(daftarItem);

        System.out.println("\nKonfirmasi Pesanan:");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-40s %-20s %-30s %-15s\n", "Kode", "Nama", "Kuantitas", "Harga", "Pajak");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------");

        for (ItemPesanan item : daftarItem) {
            System.out.printf("%-10s %-40s %-20d %-30.2f %-15.2f\n",
                    item.getKode(), item.getNama(), item.getKuantitas(),
                    item.getHargaTotal(), item.getPajakTotal());
        }

        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------");

        double totalSebelumPajak = totalMakanan + totalMinuman;
        double totalPajak = totalPajakMinuman + totalPajakMakanan;
        double totalSetelahPajak = totalSebelumPajak + totalPajak;

        System.out.println();
        System.out.printf("Nama Member            : %s\n", member.getNamaMember());
        System.out.printf("Kode Member            : %s\n", member.getKodeMember());
        System.out.printf("Total Sebelum Pajak    : %.2f IDR\n", totalSebelumPajak);
        System.out.printf("Total Pajak            : %.2f IDR\n", totalPajak);
        System.out.printf("Total Setelah Pajak    : %.2f IDR\n", totalSetelahPajak);

        return new double[] { totalMakanan, totalMinuman, totalSetelahPajak };
    }
}
