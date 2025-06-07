import java.util.*;

public class Dapur {
    public static void prosesTimDapur(
        ArrayList<String> pesananMakanan, ArrayList<Integer> kuantitasMakanan,
        ArrayList<String> pesananMinuman, ArrayList<Integer> kuantitasMinuman
    ) {
        PriorityQueue<Menu> antrianMakanan = new PriorityQueue<>((a, b) -> Double.compare(b.getHarga(), a.getHarga()));
        Stack<Menu> tumpukanMinuman = new Stack<>();

        for (int i = 0; i < pesananMakanan.size(); i++) {
            String kode = pesananMakanan.get(i);
            int qty = kuantitasMakanan.get(i);
            for (Menu m : Makanan.getDaftarMakanan()) {
                if (m.getKode().equalsIgnoreCase(kode)) {
                    for (int j = 0; j < qty; j++) {
                        antrianMakanan.add(m);
                    }
                }
            }
        }

        for (int i = 0; i < pesananMinuman.size(); i++) {
            String kode = pesananMinuman.get(i);
            int qty = kuantitasMinuman.get(i);
            for (Menu m : Minuman.getDaftarMinuman()) {
                if (m.getKode().equalsIgnoreCase(kode)) {
                    for (int j = 0; j < qty; j++) {
                        tumpukanMinuman.push(m);
                    }
                }
            }
        }

        System.out.println("\n=== PROSES TIM DAPUR ===");

        System.out.println("1. Makanan (berdasarkan harga tertinggi):");
        while (!antrianMakanan.isEmpty()) {
            Menu m = antrianMakanan.poll();
            System.out.println("- " + m.getNama() + " (Rp " + String.format("%,.2f", m.getHarga()) + ")");
        }

        System.out.println("\n2. Minuman (yang terakhir dipesan diproses dulu):");
        while (!tumpukanMinuman.isEmpty()) {
            Menu m = tumpukanMinuman.pop();
            System.out.println("- " + m.getNama() + " (Rp " + String.format("%,.2f", m.getHarga()) + ")");
        }
    }
}