import java.util.HashMap;
import java.util.Map;

public class MataUang {
    private static final Map<String, Double> nilaiTukar = new HashMap<>();

    static {
        nilaiTukar.put("USD", (double) 15000);
        nilaiTukar.put("JPY", (double) 100);
        nilaiTukar.put("MYR", (double) 4000);
        nilaiTukar.put("EUR", (double) 14000);
    }

    public static double konversiKeIDR(double jumlah, String mataUang) {
        return jumlah * nilaiTukar.getOrDefault(mataUang, (double) 1000);
    }
}
