import java.util.*;
public class InputPesanan {
    public static int prosesPesanan(
            Scanner scanner,
            String[] daftarKode,
            int[] daftarQty,
            int maxItem,
            int maxQty,
            String tipe, // "Makanan" atau "Minuman"
            Menu[] daftarMenu
    ) {
        int totalPesanan = 0;

        while (totalPesanan < maxItem) {
            S.clear();
            if (tipe.equals("Makanan")) Makanan.tampilkanDaftarMakanan();
            else Minuman.tampilkanDaftarMinuman();

            System.out.println("Masukkan kode " + tipe.toLowerCase() + " (atau 'CC' untuk membatalkan)");
            System.out.print("Jika sudah selesai, masukan 'Selesai': ");
            String kode = scanner.nextLine().trim();

            if (kode.equalsIgnoreCase("CC")) {
                System.out.println("Pesanan dibatalkan.");
                return -1;
            }

            if (kode.equalsIgnoreCase("selesai")) {
                System.out.println("Anda telah selesai memilih " + tipe.toLowerCase() + ".");
                break;
            }

            if (kode.equalsIgnoreCase("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                return -1;
            }

            boolean valid = false;
            for (Menu item : daftarMenu) {
                if (item != null && item.getKode().equalsIgnoreCase(kode)) {
                    valid = true;
                    break;
                }
            }

            if (!valid) {
                S.move(0, -70);
                System.out.println("Kode " + tipe.toLowerCase() + " tidak valid. Silakan coba lagi.");
                S.delay(2000);
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk batal, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            int kuantitas = 1;

            if (inputKuantitas.equals("0")) {
                S.move(0, -70);
                System.out.println("Pesanan dibatalkan");
                S.delay(1000);
                continue;
            }

            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            }

            try {
                kuantitas = Integer.parseInt(inputKuantitas);
                if (kuantitas < 1 || kuantitas > maxQty) {
                    System.out.println("Jumlah tidak valid. Harus antara 1 dan " + maxQty + ".");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                continue;
            }

            daftarKode[totalPesanan] = kode;
            daftarQty[totalPesanan] = kuantitas;
            totalPesanan++;
        }
        return totalPesanan;
    }
}
