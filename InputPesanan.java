import java.util.Scanner;

public class InputPesanan {
    public static int prosesPesanan(
            Scanner scanner,
            String[] daftarKode,
            int[] daftarQty,
            int maxItem,
            int maxQty,
            String tipe, 
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

            boolean valid = false;
            for (Menu item : daftarMenu) {
                if (item != null && item.getKode().equalsIgnoreCase(kode)) {
                    valid = true;
                    break;
                }
            }

            if (!valid) {
                System.out.println("Kode " + tipe.toLowerCase() + " tidak valid. Silakan coba lagi.");
                continue;
            }

            System.out.print("Masukkan jumlah (0 untuk default 1, 'S' untuk skip): ");
            String inputKuantitas = scanner.nextLine().trim();
            if (inputKuantitas.equalsIgnoreCase("S")) {
                continue;
            }

            int kuantitas = 1;  
            try {
                int parsed = Integer.parseInt(inputKuantitas);
                if (parsed == 0) {
                    kuantitas = 1;  
                } else if (parsed > 0 && parsed <= maxQty) {
                    kuantitas = parsed;
                } else {
                    System.out.println("Jumlah tidak valid. Harus antara 1 dan " + maxQty + ".");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, default kuantitas diatur ke 1.");
            }

            daftarKode[totalPesanan] = kode;
            daftarQty[totalPesanan] = kuantitas;
            totalPesanan++;
        }
        return totalPesanan;
    }
}
