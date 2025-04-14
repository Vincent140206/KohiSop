public class Makanan extends Menu {
    private static final int MAKS_MAKANAN = 10;
    public static final Makanan[] daftarMakanan = new Makanan[MAKS_MAKANAN];
    private static int jumlahMakanan = 0;

    private Makanan(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    private static void buatMakanan(String kode, String nama, double harga) {
        if (jumlahMakanan < MAKS_MAKANAN) {
            daftarMakanan[jumlahMakanan++] = new Makanan(kode, nama, harga);
        }
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
        for (int i = 0; i < jumlahMakanan; i++) {
            daftarMakanan[i].tampilkanMenu();
        }
        System.out.println("+--------+-------------------------------------+--------------+");
    }

    public static Makanan[] getDaftarMakanan() {
        return daftarMakanan;
    }
    
}