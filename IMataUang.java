import java.util.HashMap;
import java.util.Map;

public class IMataUang {
    private static final Map<String, Double> nilaiTukar = new HashMap<>();

    static {
        nilaiTukar.put("USD", (double) 15000);
        nilaiTukar.put("JPY", (double) 100);
        nilaiTukar.put("MYR", (double) 4000);
        nilaiTukar.put("EUR", (double) 14000);
    }

    public static double konversi(double totalBayar, String mataUang) {
        return totalBayar / nilaiTukar.getOrDefault(mataUang, 1.0);
    }

    public static String getSimbolMataUang(String kode) {
        switch (kode.toUpperCase()) {
            case "USD":
                return "$";
            case "JPY":
                return "¥";
            case "MYR":
                return "RM";
            case "EUR":
                return "€";
            default:
                return ""; 
        }
    }

    public static boolean cekValid(String kode) {
        return nilaiTukar.containsKey(kode.toUpperCase());
    }
}