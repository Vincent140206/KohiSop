import java.util.ArrayList;

public class Kuitansi {

    public static void tampilkan(
            ArrayList<KonfirmasiPesanan.ItemPesanan> daftarItem,
            double totalSebelumPajak,
            double totalSetelahPajak,
            double totalConvert,
            String mataUang,
            IPayment payment,
            Membership member,
            int poinSebelum,
            int poinBaru) {

        System.out.println();
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                              KUITANSI PESANAN                                    |");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("| Kode  | Nama Produk            | Qty | Harga Satuan | Pajak/Satuan | Total       |");
        System.out.println("+-------+------------------------+-----+--------------+--------------+-------------+");

        for (KonfirmasiPesanan.ItemPesanan item : daftarItem) {
            String kode = item.getKode();
            String nama = KohiSop.potongNama(item.getNama());
            int qty = item.getKuantitas();
            double hargaSatuan = item.getHargaSatuan();
            double pajakSatuan = item.getPajakSatuan();
            double totalHarga = hargaSatuan * qty;
            double totalPajak = pajakSatuan * qty;

            System.out.printf("| %-5s | %-22s | %3d | %,12.2f | %,12.2f | %,11.2f |\n",
                    kode, nama, qty, hargaSatuan, pajakSatuan, totalHarga + totalPajak);
        }

        System.out.println("+-------+------------------------+-----+--------------+--------------+-------------+");
        System.out.printf("| Subtotal sebelum pajak                                      : %,14.2f IDR |\n", totalSebelumPajak);
        System.out.printf("| Total pajak                                                 : %,14.2f IDR |\n", totalSetelahPajak - totalSebelumPajak);
        System.out.printf("| Total setelah pajak                                         : %,14.2f IDR |\n", totalSetelahPajak);
        System.out.printf("| Total dalam %-6s                                          : %13s%.2f |\n", mataUang, MataUang.getSimbolMataUang(mataUang), totalConvert);
        System.out.printf("| Metode pembayaran                                           : %18s |\n", payment.getClass().getSimpleName());
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                             INFORMASI MEMBER                                     |");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.printf("| Nama member                                                     : %-14s |\n", member.getNamaMember());
        System.out.printf("| Kode member                                                     : %-14s |\n", member.getKodeMember());
        System.out.printf("| Poin sebelum                                                    : %-14d |\n", poinSebelum);
        System.out.printf("| Poin didapat                                                    : %-14d |\n", poinBaru);
        System.out.printf("| Total poin sekarang                                             : %-14d |\n", member.getPoin());
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                          Terima kasih atas kunjungan Anda!                       |");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println();
    }
}