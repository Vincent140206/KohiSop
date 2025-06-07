import java.util.*;

public class DataPelanggan {
    public ArrayList<String> pesananMinuman = new ArrayList<>();
    public ArrayList<Integer> kuantitasMinuman = new ArrayList<>();
    public ArrayList<String> pesananMakanan = new ArrayList<>();
    public ArrayList<Integer> kuantitasMakanan = new ArrayList<>();
    public Membership member;
    public IPayment payment;
    public ArrayList<KonfirmasiPesanan.ItemPesanan> daftarItem = new ArrayList<>();
    public double totalSebelumPajak;
    public double totalSetelahPajak;
    public double totalBayar;
    public double totalConvert;
    public String mataUang;
    public int poinSebelum;
    public int poinBaru;
    public int totalQty;
    public int nomorPelanggan;

    public DataPelanggan() {}

    public DataPelanggan(int nomorPelanggan) {
        this.nomorPelanggan = nomorPelanggan;
    }

    public void hitungTotalQty() {
        totalQty = 0;
        for (int qty : kuantitasMakanan)
            totalQty += qty;
        for (int qty : kuantitasMinuman)
            totalQty += qty;
    }

    public void hitungTotalHarga() {
        totalSebelumPajak = 0;
        double totalPajak = 0;
        for (KonfirmasiPesanan.ItemPesanan item : daftarItem) {
            totalSebelumPajak += item.getHargaTotal();
            totalPajak += item.getPajakTotal();
        }
        totalSetelahPajak = totalSebelumPajak + totalPajak;
    }

    public void prosesPoin() {
        poinSebelum = member.getPoin();
        poinBaru = totalQty;
        if (member.getKodeMember().toUpperCase().contains("A")) {
            poinBaru *= 2;
        }
        member.tambahPoin(poinBaru);
    }
}