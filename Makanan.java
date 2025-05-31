import java.util.ArrayList;

public class Makanan extends Menu {
    private static final ArrayList<Makanan> daftarMakanan = new ArrayList<>();

    private Makanan(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    public static void inisialisasiMenu() {
        daftarMakanan.clear();
        daftarMakanan.add(new Makanan("M1", "Petemania Pizza", 112000));
        daftarMakanan.add(new Makanan("M2", "Mie Rebus Super Mario", 35000));
        daftarMakanan.add(new Makanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72000));
        daftarMakanan.add(new Makanan("M4", "Soto Kambing Iga Guling", 124000));
        daftarMakanan.add(new Makanan("S1", "Singkong Bakar A La Carte", 37000));
        daftarMakanan.add(new Makanan("S2", "Ubi Cilembu Bakar Arang", 58000));
        daftarMakanan.add(new Makanan("S3", "Tempe Mendoan", 18000));
        daftarMakanan.add(new Makanan("S4", "Tahu Bakso Extra Telur", 28000));
    }

    public static void tampilkanDaftarMakanan() {
        System.out.println("Daftar Makanan:");
        System.out.println("+--------+-------------------------------------+--------------+");
        System.out.printf("| %-6s | %-35s | %-12s |\n", "Kode", "Menu Makanan", "Harga (Rp)");
        System.out.println("|--------+-------------------------------------+--------------|");
        for (Makanan m : daftarMakanan) {
            m.tampilkanMenu();
        }
        System.out.println("+--------+-------------------------------------+--------------+");
    }

    public static ArrayList<Makanan> getDaftarMakanan() {
        return daftarMakanan;
    }

    public static Makanan cariByKode(String kode) {
        for (Makanan m : daftarMakanan) {
            if (m.getKode().equalsIgnoreCase(kode)) return m;
        }
        return null;
    }
}
