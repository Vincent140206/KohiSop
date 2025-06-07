import java.util.*;

public class KohiSop {
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_KUANTITAS_MINUMAN = 3;
    private static final int JUMLAH_PELANGGAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DataPelanggan[] pelangganList = new DataPelanggan[JUMLAH_PELANGGAN];
        
        ArrayList<String> semuaPesananMakanan = new ArrayList<>();
        ArrayList<Integer> semuaQtyMakanan = new ArrayList<>();
        ArrayList<String> semuaPesananMinuman = new ArrayList<>();
        ArrayList<Integer> semuaQtyMinuman = new ArrayList<>();

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();
        DatabaseMembership db = new DatabaseMembership();

        for (int pelangganKe = 0; pelangganKe < JUMLAH_PELANGGAN; pelangganKe++) {
            pelangganList[pelangganKe] = new DataPelanggan();
            DataPelanggan pelanggan = pelangganList[pelangganKe];
            
            System.out.println("\n" + "=".repeat(63));
            System.out.println(" ".repeat(20) + "MELAYANI PELANGGAN KE-" + (pelangganKe + 1));
            System.out.println("=".repeat(63));

            boolean berhasilDiproses = ProsesPemesanan.prosesPesanan(
                scanner, pelanggan, db, pelangganKe + 1,
                MAX_MAKANAN, MAX_MINUMAN, MAX_KUANTITAS_MINUMAN
            );

            if (!berhasilDiproses) {
                System.out.println("Pelanggan ke-" + (pelangganKe + 1) + " tidak dapat diproses.");
                continue;
            }

            PesananGabungan.tambahkePesananGabungan(
                pelanggan, semuaPesananMakanan, semuaQtyMakanan,
                semuaPesananMinuman, semuaQtyMinuman
            );

            System.out.println("\nPelanggan ke-" + (pelangganKe + 1) + " selesai dilayani!");
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SEMUA PELANGGAN TELAH DILAYANI - MEMPROSES PESANAN DI DAPUR");
        System.out.println("=".repeat(60));
        
        Dapur.prosesTimDapur(semuaPesananMakanan, semuaQtyMakanan, 
                           semuaPesananMinuman, semuaQtyMinuman);
    }

    public static double hitungPajak(double harga, boolean isMinuman, Membership member) {
        if (member != null && member.getKodeMember().toUpperCase().contains("A")) {
            return 0;
        }

        if (isMinuman) {
            if (harga < 50000)
                return 0;
            else if (harga <= 55000)
                return harga * 0.08;
            else
                return harga * 0.11;
        } else {
            return harga > 50000 ? harga * 0.08 : harga * 0.11;
        }
    }

    public static String potongNama(String nama) {
        if (nama.length() > 20) {
            return nama.substring(0, 17) + "...";
        }
        return nama;
    }

    public static Membership prosesMember(DatabaseMembership db, String kodeMember, String namaPembeli, int totalQty) {
        Membership member;

        if (kodeMember == null || kodeMember.isEmpty()) {
            member = db.buatMember(namaPembeli);
            System.out.println("Member baru dibuat: " + member.getKodeMember());
        } else {
            member = db.cariMember(kodeMember);
            if (member == null) {
                member = db.buatMember(namaPembeli);
                System.out.println("Member baru dibuat: " + member.getKodeMember());
            }
        }

        return member;
    }

    public static void insertionSort(ArrayList<KonfirmasiPesanan.ItemPesanan> list) {
        for (int i = 1; i < list.size(); i++) {
            KonfirmasiPesanan.ItemPesanan key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getHargaTotal() > key.getHargaTotal()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}