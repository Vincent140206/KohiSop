
import java.util.*;

public class KohiSop { 
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_KUANTITAS_MINUMAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> pesananMinuman = new ArrayList<>();
        ArrayList<Integer> kuantitasMinuman = new ArrayList<>();
        ArrayList<String> pesananMakanan = new ArrayList<>();
        ArrayList<Integer> kuantitasMakanan = new ArrayList<>();

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();

        while (pesananMinuman.size() < MAX_MINUMAN) {
            S.clear();
            Minuman.tampilkanDaftarMinuman();
            System.out.println("Masukkan kode minuman (atau 'CC' untuk membatalkan)");
            System.out.print("Jika sudah selesai, masukan 'Selesai': ");
            String kodeMinuman = scanner.nextLine().trim();

            if (kodeMinuman.equalsIgnoreCase("CC")) {
                System.out.println("Pesanan dibatalkan.");
                return;
            }

            if (kodeMinuman.equalsIgnoreCase("selesai")) {
                System.out.println("Anda telah selesai memilih minuman.");
                break;
            }

            boolean valid = false;
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kodeMinuman)) {
                    valid = true;
                    pesananMinuman.add(kodeMinuman);
                    break;
                }
            }

            if (!valid) {
                System.out.println("Kode minuman tidak valid. Silakan coba lagi.");
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk batal, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            int kuantitas = 1; 

            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            } else if (!inputKuantitas.isEmpty()) {
                try {
                    kuantitas = Integer.parseInt(inputKuantitas);
                    if (kuantitas < 0 || kuantitas > MAX_KUANTITAS_MINUMAN) {
                        System.out.println("Jumlah tidak valid. Harus antara 0 dan " + MAX_KUANTITAS_MINUMAN + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    continue;
                }
            }

            kuantitasMinuman.add(kuantitas);
        }

        while (pesananMakanan.size() < MAX_MAKANAN) {
            S.clear();
            Makanan.tampilkanDaftarMakanan();
            System.out.println("Masukkan kode minuman (atau 'CC' untuk membatalkan)");
            System.out.print("Jika sudah selesai, masukan 'Selesai': ");
            String kodeMakanan = scanner.nextLine().trim();

            if (kodeMakanan.equalsIgnoreCase("CC")) {
                System.out.println("Pesanan dibatalkan.");
                return;
            }

            if (kodeMakanan.equalsIgnoreCase("selesai")) {
                System.out.println("Anda telah selesai memilih makanan.");
                break;
            }

            boolean valid = false;
            for (Makanan makanan : Makanan.daftarMakanan) {
                if (makanan.getKode().equalsIgnoreCase(kodeMakanan)) {
                    valid = true;
                    pesananMakanan.add(kodeMakanan);
                    break;
                }
            }

            if (!valid) {
                System.out.println("Kode makanan tidak valid. Silakan coba lagi.");
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk batal, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            int kuantitas = 1; 

            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            } else if (!inputKuantitas.isEmpty()) {
                try {
                    kuantitas = Integer.parseInt(inputKuantitas);
                    if (kuantitas < 0 || kuantitas > MAX_MAKANAN) {
                        System.out.println("Jumlah tidak valid. Harus antara 0 dan " + MAX_MAKANAN + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    continue;
                }
            }

            kuantitasMakanan.add(kuantitas);
        }

        S.clear();
        System.out.println("\nDaftar Pesanan Minuman:");
        System.out.printf("%-10s %-30s %-10s\n", "Kode", "Minuman", "Kuantitas");
        for (int i = 0; i < pesananMinuman.size(); i++) {
            String kode = pesananMinuman.get(i);
            int kuantitas = kuantitasMinuman.get(i);
            for (Minuman minuman : Minuman.daftarMinuman) {
                if (minuman.getKode().equalsIgnoreCase(kode)) {
                    System.out.printf("%-10s %-30s %-10d\n", kode, minuman.getNama(), kuantitas);
                }
            }
        }

        System.out.println("\nDaftar Pesanan Makanan:");
        System.out.printf("%-10s %-30s %-10s\n", "Kode", "Makanan", "Kuantitas");
        for (int i = 0; i < pesananMakanan.size(); i++) {
            String kode = pesananMakanan.get(i);
            int kuantitas = kuantitasMakanan.get(i);
            for (Makanan makanan : Makanan.daftarMakanan) {
                if (makanan.getKode().equalsIgnoreCase(kode)) {
                    System.out.printf("%-10s %-30s %-10d\n", kode, makanan.getNama(), kuantitas);
                }
            }
        }

        System.out.println("Terima kasih atas pesanan Anda!");
    }
}