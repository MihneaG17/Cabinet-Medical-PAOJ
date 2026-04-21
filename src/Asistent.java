import java.time.LocalDate;

public class Asistent extends PersonalMedical{
    private String gradProfesional;
    private boolean poateRecolta;

    public Asistent(String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String gradProfesional, boolean poateRecolta) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
        this.gradProfesional=gradProfesional;
        this.poateRecolta=poateRecolta;
    }

    public String getGradProfesional() {
        return gradProfesional;
    }

    public void setGradProfesional(String gradProfesional) {
        this.gradProfesional = gradProfesional;
    }

    public boolean isPoateRecolta() {
        return poateRecolta;
    }

    public void setPoateRecolta(boolean poateRecolta) {
        this.poateRecolta = poateRecolta;
    }
}
