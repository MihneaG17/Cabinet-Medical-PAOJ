import java.util.List;
import java.time.LocalDate;

public abstract class PersonalMedical extends Persoana{
    protected double salariu;
    protected String tura;
    protected LocalDate dataAngajarii;

    public PersonalMedical(String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii) {
        super(nume, prenume, cnp, nrTelefon);
        this.salariu=salariu;
        this.tura=tura;
        this.dataAngajarii=dataAngajarii;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getTura() {
        return tura;
    }

    public void setTura(String tura) {
        this.tura = tura;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }
}
