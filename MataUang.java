public class MataUang {
    private static final String[] kodeMataUang = {"USD", "JPY", "MYR", "EUR"};
    private static final double[] nilaiTukar = {15000.0, 100.0, 4000.0, 14000.0};

    public static double konversi(double totalBayar, String mataUang) {
        mataUang = mataUang.toUpperCase();
        for (int i = 0; i < kodeMataUang.length; i++) {
            if (kodeMataUang[i].equals(mataUang)) {
                return totalBayar / nilaiTukar[i];
            }
        }
        return totalBayar; 
    }

    public static String getSimbolMataUang(String kode) {
        kode = kode.toUpperCase();
        switch (kode) {
            case "USD": return "$";
            case "JPY": return "¥";
            case "MYR": return "RM";
            case "EUR": return "€";
            default: return ""; 
        }
    }

    public static boolean cekValid(String kode) {
        kode = kode.toUpperCase();
        for (String k : kodeMataUang) {
            if (k.equals(kode)) {
                return true;
            }
        }
        return false;
    }
}
