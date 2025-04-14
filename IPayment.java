public interface IPayment {
    double hitungTotal(double totalPesanan);
    boolean cekSaldo(double totalPesanan);

    String getNamaMetode();      
    double getDiskon();        
    double getBiayaAdmin();  
}
