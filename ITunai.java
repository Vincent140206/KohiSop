public class ITunai implements Payment{
    
    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan;
    }

    @Override
    public boolean cekSaldo(double totalPesanan) {
        return true;
    }
    
}