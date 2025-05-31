import java.util.ArrayList;

public class Minuman extends Menu {
    private static final ArrayList<Minuman> daftarMinuman = new ArrayList<>();

    private Minuman(String kode, String nama, double harga) {
        super(kode, nama, harga);
    }

    public static void inisialisasiMenu() {
        daftarMinuman.clear();
        daftarMinuman.add(new Minuman("A1", "Caffe Latte", 46000));
        daftarMinuman.add(new Minuman("A2", "Cappuccino", 46000));
        daftarMinuman.add(new Minuman("E1", "Caffe Americano", 37000));
        daftarMinuman.add(new Minuman("E2", "Caffe Mocha", 55000));
        daftarMinuman.add(new Minuman("E3", "Caramel Macchiato", 59000));
        daftarMinuman.add(new Minuman("E4", "Asian Dolce Latte", 55000));
        daftarMinuman.add(new Minuman("E5", "Double Shots Iced Shaken Espresso", 50000));
        daftarMinuman.add(new Minuman("B1", "Freshly Brewed Coffee", 23000));
        daftarMinuman.add(new Minuman("B2", "Vanilla Sweet Cream Cold Brew", 50000));
        daftarMinuman.add(new Minuman("B3", "Cold Brew", 44000));
    }

    public static void tampilkanDaftarMinuman() {
        System.out.println("Daftar Minuman:");
        System.out.println("+--------+-------------------------------------+--------------+");
        System.out.printf("| %-6s | %-35s | %-12s |\n", "Kode", "Menu Minuman", "Harga (Rp)");
        System.out.println("|--------+-------------------------------------+--------------|");
        for (Minuman m : daftarMinuman) {
            m.tampilkanMenu();
        }
        System.out.println("+--------+-------------------------------------+--------------+");
    }

    public static ArrayList<Minuman> getDaftarMinuman() {
        return daftarMinuman;
    }

    public static Minuman cariByKode(String kode) {
        for (Minuman m : daftarMinuman) {
            if (m.getKode().equalsIgnoreCase(kode)) return m;
        }
        return null;
    }
}
