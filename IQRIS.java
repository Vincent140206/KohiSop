public class IQRIS implements Payment{
    
    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan * 0.95;
    }

    @Override
    public boolean cekSaldo(double totalPesanan) {
        return true;
    }
    
}