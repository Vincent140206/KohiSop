import java.util.*;

public class PesananGabungan {
    
    public static void tambahkePesananGabungan(DataPelanggan pelanggan, ArrayList<String> semuaPesananMakanan, ArrayList<Integer> semuaQtyMakanan, ArrayList<String> semuaPesananMinuman, ArrayList<Integer> semuaQtyMinuman) {
        // Menambahkan pesanan makanan (FIFO - berdasarkan prioritas harga di dapur)
        for (int i = 0; i < pelanggan.pesananMakanan.size(); i++) {
            semuaPesananMakanan.add(pelanggan.pesananMakanan.get(i));
            semuaQtyMakanan.add(pelanggan.kuantitasMakanan.get(i));
        }
        
        // Menambahkan pesanan minuman (LIFO - Last-Ordered-First-Served)
        for (int i = pelanggan.pesananMinuman.size() - 1; i >= 0; i--) {
            semuaPesananMinuman.add(0, pelanggan.pesananMinuman.get(i));
            semuaQtyMinuman.add(0, pelanggan.kuantitasMinuman.get(i));
        }
        
    }
    
    public static void tampilkanStatusAntrian(ArrayList<String> semuaPesananMakanan,
                                            ArrayList<Integer> semuaQtyMakanan,
                                            ArrayList<String> semuaPesananMinuman,
                                            ArrayList<Integer> semuaQtyMinuman) {
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("STATUS ANTRIAN DAPUR");
        System.out.println("=".repeat(50));
        
        System.out.println("ANTRIAN MAKANAN (berdasarkan prioritas harga):");
        for (int i = 0; i < semuaPesananMakanan.size(); i++) {
            System.out.println((i + 1) + ". " + semuaPesananMakanan.get(i) + 
                             " (Qty: " + semuaQtyMakanan.get(i) + ")");
        }
        
        System.out.println("\nANTRIAN MINUMAN (Last-Ordered-First-Served):");
        for (int i = 0; i < semuaPesananMinuman.size(); i++) {
            System.out.println((i + 1) + ". " + semuaPesananMinuman.get(i) + 
                             " (Qty: " + semuaQtyMinuman.get(i) + ")");
        }
        
        System.out.println("=".repeat(50));
    }
}