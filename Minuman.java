import java.util.ArrayList;

public class Minuman extends Menu {
    public static final ArrayList<Minuman> daftarMinuman = new ArrayList<>();

    private Minuman(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    private static void buatMinuman(String kode, String nama, double harga) {
        Minuman minuman = new Minuman(kode, nama, harga);
        daftarMinuman.add(minuman);
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
        System.out.printf("%-10s %-35s %-10s\n", "Kode", "Nama", "Harga");
        System.out.println("----------------------------------------------------------");
        for (Minuman minuman : daftarMinuman) {
            minuman.tampilkanMenu();
        }
        System.out.println();
    }
}