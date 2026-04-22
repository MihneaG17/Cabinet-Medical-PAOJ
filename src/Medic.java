import java.time.LocalDate;

public class Medic extends PersonalMedical{
    private String specializare;
    private double costConsultatie;
    private String codParafa;

    public Medic(String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String specializare, double costConsultatie, String codParafa) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
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
