public class IeMoney implements Payment{

    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan * 0.93 + 20000;
    }

    @Override
    public boolean cekSaldo(double totalPesanan) {
        return true;
    }

}