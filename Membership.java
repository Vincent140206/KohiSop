import java.util.Random;
import java.util.Scanner;

public class Membership {
    private String kodeMember;
    private String namaMember;
    private int poin;

    public Membership(String nama) {
        this.namaMember = nama;
        this.kodeMember = buatKodeAcak();
        this.poin = 0;
    }

    public Membership(String nama, String kodeMember, int poin) {
        this.namaMember = nama;
        this.kodeMember = kodeMember;
        this.poin = poin;
    }

    public String getKodeMember() {
        return kodeMember;
    }

    public String getNamaMember() {
        return namaMember;
    }

    public int getPoin() {
        return poin;
    }

    public void tambahPoin(int poinBaru) {
        this.poin += poinBaru;
    }

    public void kurangiPoin(int jumlah) {
        this.poin = Math.max(0, this.poin - jumlah);
    }

    private String buatKodeAcak() {
        String karakter = "ABCDEF0123456789";
        Random random = new Random();
        StringBuilder kode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int idx = random.nextInt(karakter.length());
            kode.append(karakter.charAt(idx));
        }

        return kode.toString();
    }

    @Override
    public String toString() {
        return namaMember + " (" + kodeMember + ") - Poin: " + poin;
    }

    public static Membership inputDanProsesMembership(Scanner scanner, DatabaseMembership db, int totalQty) {
        System.out.println();
        System.out.println("+----------------------------------+");
        System.out.println("|        DAFTAR MEMBER             |");
        System.out.println("+----------------------------------+");
        System.out.println("| Nama     | Kode Member | Poin    |");
        System.out.println("+----------+-------------+---------+");
        System.out.println("| Pinsen   | A1B2C3      | 20 poin |");
        System.out.println("| Ayaya    | B4C5D6      | 10 poin |");
        System.out.println("+----------+-------------+---------+");
        System.out.println();

        Membership member = null;

        while (member == null) {
            System.out.print("Masukkan kode member (kosong jika baru): ");
            String kodeMember = scanner.nextLine().trim();

            if (kodeMember.isEmpty()) {
                System.out.print("Masukkan nama pembeli: ");
                String namaPembeli = scanner.nextLine().trim();
                member = new Membership(namaPembeli);
            } else {
                Membership ditemukan = db.cariMember(kodeMember);
                if (ditemukan != null) {
                    member = ditemukan;
                } else {
                    System.out.println("Kode member tidak ditemukan. Silakan coba lagi.\n");
                }
            }
        }

        return member;
    }
}