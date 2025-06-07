import java.util.ArrayList;

public class DatabaseMembership {
    private ArrayList<Membership> daftarMember = new ArrayList<>();

    public DatabaseMembership(){
        daftarMember.add(new Membership("Pinsen", "A1B2C3", 20));  
        daftarMember.add(new Membership("Ayaya", "B4C5D6", 10));    
    }

    public Membership cariMember(String kode) {
        for (int i = 0; i < daftarMember.size(); i++) {
            Membership m = daftarMember.get(i);
            if (m.getKodeMember().equalsIgnoreCase(kode)) {
                return m;
            }
        }
        return null;
    }


    public Membership buatMember(String nama) {
        Membership m = new Membership(nama);
        daftarMember.add(m);
        return m;
    }
}