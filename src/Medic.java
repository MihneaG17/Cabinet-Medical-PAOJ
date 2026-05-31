import java.time.LocalDate;

public class Medic extends PersonalMedical{
    private int idMedic;
    private String specializare;
    private double costConsultatie;
    private String codParafa;

    public Medic(String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String specializare, double costConsultatie, String codParafa) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
        this.idMedic=0;
        this.specializare=specializare;
        this.costConsultatie=costConsultatie;
        this.codParafa=codParafa;
    }

    //constructor pentru baza de date
    public Medic(int idMedic, String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String specializare, double costConsultatie, String codParafa) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
        this.idMedic=idMedic;
        this.specializare=specializare;
        this.costConsultatie=costConsultatie;
        this.codParafa=codParafa;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public double getCostConsultatie() {
        return costConsultatie;
    }

    public void setCostConsultatie(double costConsultatie) {
        this.costConsultatie = costConsultatie;
    }

    public String getCodParafa() {
        return codParafa;
    }

    public void setCodParafa(String codParafa) {
        this.codParafa = codParafa;
    }

    public String getPrezentarePublica() {
        return "Dr. " + getNume() + " " + getPrenume() +
                " | Specializare: " + specializare +
                " | Cost consult: " + costConsultatie + " RON";
    }

    @Override
    public String toString() {
        return "Dr. " + getNume() + " " + getPrenume() +
                " | Nr. Telefon: " + getNrTelefon() +
                " | Specializare: " + specializare +
                " | Cost consult: " + costConsultatie + " RON" +
                " | Salariu: " + salariu +
                " | Tura: " + tura +
                " | Cod Parafa: " + codParafa;
    }
}
