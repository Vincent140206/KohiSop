public class eMoney implements IPayment {

    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan * (1 - getDiskon()) + getBiayaAdmin(); 
    }

    @Override
    public double getBiayaAdmin() {
        return 20000; 
    }

    @Override
    public boolean cekSaldo(double totalPesanan) {
        return true;
    }

    @Override
    public String getNamaMetode() {
        return "eMoney";
    }

    @Override
    public double getDiskon() {
        return 0.07; 
    }
}
