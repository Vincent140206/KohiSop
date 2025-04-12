public class Tunai implements Payment {
    @Override
    public double hitungTotal(double totalPesanan) {
        return totalPesanan - (totalPesanan * getDiskon());
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
        return "Tunai";
    }

    @Override
    public double getDiskon() {
        return 0.0;
    }
}
