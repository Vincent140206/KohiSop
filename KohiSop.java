import java.util.*;

public class KohiSop {
    private static final int MAX_MAKANAN = 2;
    private static final int MAX_MINUMAN = 5;
    private static final int MAX_KUANTITAS_MINUMAN = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> pesananMinuman = new ArrayList<>();
        ArrayList<Integer> kuantitasMinuman = new ArrayList<>();
        ArrayList<String> pesananMakanan = new ArrayList<>();
        ArrayList<Integer> kuantitasMakanan = new ArrayList<>();

        Makanan.inisialisasiMenu();
        Minuman.inisialisasiMenu();

        int pesanan_makanan = InputPesanan.prosesPesanan(
                scanner,
                pesananMakanan,
                kuantitasMakanan,
                MAX_MAKANAN,
                MAX_MAKANAN,
                "Makanan",
                new ArrayList<Menu>(Makanan.getDaftarMakanan()));
        if (pesanan_makanan == -1)
            return;

        int pesanan_minuman = InputPesanan.prosesPesanan(
                scanner,
                pesananMinuman,
                kuantitasMinuman,
                MAX_MINUMAN,
                MAX_KUANTITAS_MINUMAN,
                "Minuman",
                new ArrayList<Menu>(Minuman.getDaftarMinuman()));
        if (pesanan_minuman == -1)
            return;

        int totalQty = 0;
        for (int qty : kuantitasMakanan)
            totalQty += qty;
        for (int qty : kuantitasMinuman)
            totalQty += qty;
            
        DatabaseMembership db = new DatabaseMembership();
        S.clear();
        Membership member = Membership.inputDanProsesMembership(scanner, db, totalQty);

        ArrayList<KonfirmasiPesanan.ItemPesanan> daftarItem = new ArrayList<>();

        for (int i = 0; i < pesananMakanan.size(); i++) {
            String kode = pesananMakanan.get(i);
            int qty = kuantitasMakanan.get(i);
            for (Makanan makanan : Makanan.getDaftarMakanan()) {
                if (makanan != null && makanan.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = makanan.getHarga();
                    double pajakSatuan = hitungPajak(hargaSatuan, false, member);
                    daftarItem.add(new KonfirmasiPesanan.ItemPesanan(
                            kode.toUpperCase(),
                            makanan.getNama(),
                            qty,
                            hargaSatuan,
                            pajakSatuan,
                            false));
                    break;
                }
            }
        }

        for (int i = 0; i < pesananMinuman.size(); i++) {
            String kode = pesananMinuman.get(i);
            int qty = kuantitasMinuman.get(i);
            for (Minuman minuman : Minuman.getDaftarMinuman()) {
                if (minuman != null && minuman.getKode().equalsIgnoreCase(kode)) {
                    double hargaSatuan = minuman.getHarga();
                    double pajakSatuan = hitungPajak(hargaSatuan, true, member);
                    daftarItem.add(new KonfirmasiPesanan.ItemPesanan(
                            kode.toUpperCase(),
                            minuman.getNama(),
                            qty,
                            hargaSatuan,
                            pajakSatuan,
                            true));
                    break;
                }
            }
        }

        insertionSort(daftarItem);

        KonfirmasiPesanan.tampilkan(
                pesananMakanan, kuantitasMakanan,
                pesananMinuman, kuantitasMinuman,
                member);

        double totalSebelumPajak = 0;
        double totalPajak = 0;
        for (KonfirmasiPesanan.ItemPesanan item : daftarItem) {
            totalSebelumPajak += item.getHargaTotal();
            totalPajak += item.getPajakTotal();
        }
        double totalSetelahPajak = totalSebelumPajak + totalPajak;

        IPayment payment = null;
        while (true) {
            System.out.println("\nPilih metode pembayaran: ");
            System.out.println("1. Tunai");
            System.out.println("2. QRIS");
            System.out.println("3. eMoney");
            System.out.print("Pilihan Anda: ");
            String input = scanner.nextLine().trim();

            try {
                int pilihan = Integer.parseInt(input);
                switch (pilihan) {
                    case 1 -> payment = new Tunai();
                    case 2 -> payment = new QRIS();
                    case 3 -> payment = new eMoney();
                    default -> {
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak sesuai. Harap masukkan angka.");
            }
        }

        if (payment == null || !payment.cekSaldo(totalSetelahPajak)) {
            System.out.println("Saldo tidak cukup untuk melakukan pembayaran atau metode pembayaran tidak valid.");
            return;
        }

        double totalBayar = payment.hitungTotal(totalSetelahPajak);
        System.out.printf("\nTotal yang harus dibayar            : %.2f IDR\n", totalBayar);

        System.out.print("Pilih mata uang (USD, JPY, MYR, EUR): ");
        String mataUang = scanner.nextLine().trim().toUpperCase();
        while (!MataUang.cekValid(mataUang)) {
            System.out.print("Mata uang tidak valid. Silakan pilih mata uang (USD, JPY, MYR, EUR): ");
            mataUang = scanner.nextLine().trim().toUpperCase();
        }

        double totalConvert = MataUang.konversi(totalBayar, mataUang);
        String simbol = MataUang.getSimbolMataUang(mataUang);
        System.out.printf("Total dalam %s: %s%,.2f\n", mataUang, simbol, totalConvert);

        int poinSebelum = member.getPoin();
        int poinBaru = totalQty;

        if (member.getKodeMember().toUpperCase().contains("A")) {
            poinBaru *= 2;
        }

        member.tambahPoin(poinBaru);       

        S.clear(); 

        Kuitansi.tampilkan(
            daftarItem,
            totalSebelumPajak,
            totalSetelahPajak,
            totalConvert,
            mataUang,
            payment,
            member,
            poinSebelum,
            poinBaru
        );
    }
    
    public static double hitungPajak(double harga, boolean isMinuman, Membership member) {
        if (member != null && member.getKodeMember().toUpperCase().contains("A")) {
            return 0;
        }

        if (isMinuman) {
            if (harga < 50000)
                return 0;
            else if (harga <= 55000)
                return harga * 0.08;
            else
                return harga * 0.11;
        } else {
            return harga > 50000 ? harga * 0.08 : harga * 0.11;
        }
    }

    public static String potongNama(String nama) {
        if (nama.length() > 20) {
            return nama.substring(0, 17) + "...";
        }
        return nama;
    }

    public static Membership prosesMember(DatabaseMembership db, String kodeMember, String namaPembeli, int totalQty) {
        Membership member;

        if (kodeMember == null || kodeMember.isEmpty()) {
            member = db.buatMember(namaPembeli);
            System.out.println("Member baru dibuat: " + member.getKodeMember());
        } else {
            member = db.cariMember(kodeMember);
            if (member == null) {
                member = db.buatMember(namaPembeli);
                System.out.println("Member baru dibuat: " + member.getKodeMember());
            }
        }

        return member;
    }

    public static void insertionSort(ArrayList<KonfirmasiPesanan.ItemPesanan> list) {
        for (int i = 1; i < list.size(); i++) {
            KonfirmasiPesanan.ItemPesanan key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getHargaTotal() > key.getHargaTotal()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}