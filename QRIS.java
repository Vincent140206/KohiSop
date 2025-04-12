public class QRIS implements Payment {

    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan * (1 - getDiskon());
    }

    @Override
    public double getBiayaAdmin() {
        return 0; 
    }

    @Override
    public boolean cekSaldo(double totalPesanan) {
        return true;
    }

    @Override
    public String getNamaMetode() {
        return "QRIS";
    }

    @Override
    public double getDiskon() {
        return 0.05; 
    }
}
