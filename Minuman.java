public class Minuman extends Menu {
    private static final int MAKS_MINUMAN = 10;
    public static final Minuman[] daftarMinuman = new Minuman[MAKS_MINUMAN];
    private static int jumlahMinuman = 0;

    private Minuman(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    private static void buatMinuman(String kode, String nama, double harga) {
        if (jumlahMinuman < MAKS_MINUMAN) {
            daftarMinuman[jumlahMinuman++] = new Minuman(kode, nama, harga);
        }
    }

    public static void inisialisasiMenu() {
        buatMinuman("A1", "Caffe Latte", 46000);
        buatMinuman("A2", "Cappuccino", 46000);
        buatMinuman("E1", "Caffe Americano", 37000);
        buatMinuman("E2", "Caffe Mocha", 55000);
        buatMinuman("E3", "Caramel Macchiato", 59000);
        buatMinuman("E4", "Asian Dolce Latte", 55000);
        buatMinuman("E5", "Double Shots Iced Shaken Espresso", 50000);
        buatMinuman("B1", "Freshly Brewed Coffee", 23000);
        buatMinuman("B2", "Vanilla Sweet Cream Cold Brew", 50000);
        buatMinuman("B3", "Cold Brew", 44000);
    }

    public static void tampilkanDaftarMinuman() {
        System.out.println("Daftar Minuman:");
        System.out.println("+--------+-------------------------------------+--------------+");
        System.out.printf("| %-6s | %-35s | %-12s |\n", "Kode", "Menu Minuman", "Harga (Rp)");
        System.out.println("|--------+-------------------------------------+--------------|");
        for (int i = 0; i < jumlahMinuman; i++) {
            daftarMinuman[i].tampilkanMenu();
        }
        System.out.println("+--------+-------------------------------------+--------------+");
    }

    public static Minuman[] getDaftarMinuman() {
            return daftarMinuman;
    }
}