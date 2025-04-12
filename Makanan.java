import java.util.ArrayList;

public class Makanan extends Menu {
    public static final ArrayList<Makanan> daftarMakanan = new ArrayList<>();

    private Makanan(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    private static void buatMakanan(String kode, String nama, double harga) {
        Makanan makanan = new Makanan(kode, nama, harga);
        daftarMakanan.add(makanan);
    }

    public static void inisialisasiMenu() {
        buatMakanan("M1", "Petemania Pizza", 112000);
        buatMakanan("M2", "Mie Rebus Super Mario", 35000);
        buatMakanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72000);
        buatMakanan("M4", "Soto Kambing Iga Guling", 124000);
        buatMakanan("S1", "Singkong Bakar A La Carte", 37000);
        buatMakanan("S2", "Ubi Cilembu Bakar Arang", 58000);
        buatMakanan("S3", "Tempe Mendoan", 18000);
        buatMakanan("S4", "Tahu Bakso Extra Telur", 28000);
    }

    public static void tampilkanDaftarMakanan() {
        System.out.println("Daftar Makanan:");
        System.out.println("+--------+-------------------------------------+--------------+");
    System.out.printf("| %-6s | %-35s | %-12s |\n", "Kode", "Menu Makanan", "Harga (Rp)");
    System.out.println("|--------+-------------------------------------+--------------|");
        for (Makanan makanan : daftarMakanan) {
            makanan.tampilkanMenu();
        }   
    System.out.println("+--------+-------------------------------------+--------------+");
    }
}
