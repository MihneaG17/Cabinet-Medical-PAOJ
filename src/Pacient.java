import java.util.ArrayList;
import java.util.List;

public class Pacient extends Persoana {
    private String grupaSanguina;
    private List<String> istoricAlergii;
    private FisaMedicala fisaMedicala;

    public Pacient(String nume, String prenume, String cnp, String nrTelefon, String grupaSanguina, List<String> istoricAlergii) {
        super(nume, prenume, cnp, nrTelefon);
        this.grupaSanguina=grupaSanguina;
        if (istoricAlergii == null) {
            this.istoricAlergii = new ArrayList<>();
        } else {
            this.istoricAlergii = istoricAlergii;
        }
        this.fisaMedicala=new FisaMedicala();
    }

    public String getGrupaSanguina() {
        return grupaSanguina;
    }

    public void setGrupaSanguina(String grupaSanguina) {
        this.grupaSanguina = grupaSanguina;
    }

    public List<String> getIstoricAlergii() {
        return istoricAlergii;
    }

    public FisaMedicala getFisaMedicala() {
        return fisaMedicala;
    }

    public void addIstoricAlergii(String alergie) {
        this.istoricAlergii.add(alergie);
    }
}
