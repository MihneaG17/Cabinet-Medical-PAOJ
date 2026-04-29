import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CabinetService {
    private List<Medic> medici;
    private List<Pacient> pacienti;
    private List<Asistent> asistenti;
    private List<ServiciuMedical> servicii;
    private List<Sala> sali;
    private List<Programare> programari;
    private List<Factura> facturi;

    public CabinetService() {
        this.medici = new ArrayList<>();
        this.pacienti = new ArrayList<>();
        this.asistenti = new ArrayList<>();
        this.servicii = new ArrayList<>();
        this.sali = new ArrayList<>();
        this.programari = new ArrayList<>();
        this.facturi=new ArrayList<>();

        populareDate();
    }

    //Actiuni medici

    public void adaugaMedic(Medic medicNou) {
        medici.add(medicNou);
    }

    public List<Medic> getMedici() {
        return medici;
    }

    public boolean concediazaMedic(String cnp) {
        for (int i = 0; i < medici.size(); i++) {
            Medic medicCurent = medici.get(i);

            if (medicCurent.getCnp().equals(cnp)) {
                medici.remove(i);
                return true;
            }
        }
        return false;
    }

    public Medic cautaMedicDupaCnp(String cnpCautat) {
        for(Medic m : medici) {
            if(m.getCnp().equals(cnpCautat)) {
                return m;
            }
        }
        return null;
    }

    public List<Programare> getToateProgramarile() {
        return programari;
    }

    public void adaugaFactura(Factura f) { facturi.add(f); }

    //Actiuni asistenti
    public void adaugaAsistent(Asistent asistent) {
        asistenti.add(asistent);
    }

    public List<Asistent> getAsistenti() {
        return asistenti;
    }

    public boolean concediazaAsistent(String cnp) {
        for(int i=0;i<asistenti.size();i++) {
            if(asistenti.get(i).getCnp().equals(cnp)) {
                asistenti.remove(i);
                return true;
            }
        }
        return false;
    }

    //Actiuni pacienti

    public void adaugaPacient(Pacient pacient) {
        pacienti.add(pacient);
    }

    public Pacient cautaPacientDupaCnp(String cnp) {
        for (Pacient p : pacienti) {
            if (p.getCnp().equals(cnp)) {
                return p;
            }
        }
        return null;
    }

    public void adaugaProgramare(Programare p) {
        programari.add(p);
        p.getPacient().getFisaMedicala().adaugaProgramare(p);
    }

    public List<ServiciuMedical> getServicii() {
        return servicii;
    }

    public List<Sala> getSali() {
        return sali;
    }

    public List<Factura> getFacturi() { return facturi; }

    private void populareDate() {
        LocalDate azi = LocalDate.now();

        Sala s1=new Sala(101, 1, "Consultatii");
        Sala s2=new Sala(201, 2, "Chirurgie");
        sali.add(s1);
        sali.add(s2);

        ServiciuMedical serv1=new ServiciuMedical("Consultatie cardiologie", 250.0, 30);
        ServiciuMedical serv2=new ServiciuMedical("Ecografie", 150.0, 20);
        servicii.add(serv1);
        servicii.add(serv2);

        Medic m1 = new Medic("Popescu", "Ion", "1800101123456", "0722000000", 8500.0, "Zi",
                LocalDate.of(2018, 5, 20), "Cardiologie", 250.0, "1729");

        Medic m2 = new Medic("Ionescu", "Maria", "2850505123456", "0733000000", 7200.0, "Noapte",
                LocalDate.of(2020, 11, 10), "Ecografie", 150.0, "1092");

        Medic m3 = new Medic("Andreescu", "Vasile", "1790909123456", "0744000000", 9000.0, "Zi",
                LocalDate.of(2015, 2, 15), "Chirurgie", 400.0, "2909");

        adaugaMedic(m1);
        adaugaMedic(m2);
        adaugaMedic(m3);

        List<String> alergii1 = new ArrayList<>();
        alergii1.add("Penicilina");
        alergii1.add("Polen");

        Pacient p1 = new Pacient("Dumitru", "Andrei", "1950101123456", "0755111222", "A2", alergii1);
        Pacient p2 = new Pacient("Stan", "Elena", "2980202123456", "0766333444", "01", null); // Fără alergii

        pacienti.add(p1);
        pacienti.add(p2);

        Asistent a1 = new Asistent("Georgescu", "Alina", "2880303123456", "0788111222", 4500.0, "Zi",
                LocalDate.of(2021, 1, 15), "Principal", true);
        Asistent a2 = new Asistent("Marin", "Marius", "1920404123456", "0799333444", 4000.0, "Noapte",
                LocalDate.of(2023, 6, 10), "Debutant", false);

        asistenti.add(a1);
        asistenti.add(a2);

        Programare prog1 = new Programare(p1, m1, serv1, s1, java.time.LocalDateTime.of(azi.getYear(), azi.getMonthValue(), azi.getDayOfMonth(), 10, 30));
        Programare prog2 = new Programare(p2, m2, serv2, s2, java.time.LocalDateTime.of(2026, 4, 26, 14, 0));
        Programare prog3 = new Programare(p1, m3, serv1, s1, java.time.LocalDateTime.of(2026, 4, 10, 9, 0));

        programari.add(prog1);
        programari.add(prog2);
        programari.add(prog3);

        p1.getFisaMedicala().adaugaProgramare(prog1);
        p2.getFisaMedicala().adaugaProgramare(prog2);
        p1.getFisaMedicala().adaugaProgramare(prog3);

        double suma1 = prog1.getMedic().getCostConsultatie() + prog1.getServiciu().getPret();
        Factura f1 = new Factura(suma1, LocalDate.of(2026, 4, 25), prog1);

        double suma2 = prog2.getMedic().getCostConsultatie() + prog2.getServiciu().getPret();
        Factura f2 = new Factura(suma2, LocalDate.of(2026, 4, 26), prog2);

        Factura f3 = new Factura(300.0, LocalDate.of(2026, 4, 10), prog3);
        f3.setStatusPlata(Factura.STATUS_PLATA_FINALIZATA);

        facturi.add(f1);
        facturi.add(f2);
        facturi.add(f3);
    }
}
