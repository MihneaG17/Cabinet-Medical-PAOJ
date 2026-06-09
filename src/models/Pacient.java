package models;

import java.util.ArrayList;
import java.util.List;

public class Pacient extends Persoana {
    private int idPacient;
    private String grupaSanguina;
    private List<String> istoricAlergii;
    private FisaMedicala fisaMedicala;
    private String parola;

    public Pacient(String nume, String prenume, String cnp, String nrTelefon, String grupaSanguina, List<String> istoricAlergii, String parola) {
        super(nume, prenume, cnp, nrTelefon);
        this.idPacient=0;
        this.grupaSanguina=grupaSanguina;
        if (istoricAlergii == null) {
            this.istoricAlergii = new ArrayList<>();
        } else {
            this.istoricAlergii = istoricAlergii;
        }
        this.fisaMedicala=new FisaMedicala();
        this.parola=parola;
    }

    //constructor pentru baza de date in care retin si id-ul pacientului
    public Pacient(int idPacient, String nume, String prenume, String cnp, String nrTelefon, String grupaSanguina, List<String> istoricAlergii, String parola) {
        super(nume, prenume, cnp, nrTelefon);
        this.idPacient=idPacient;
        this.grupaSanguina=grupaSanguina;
        if (istoricAlergii == null) {
            this.istoricAlergii = new ArrayList<>();
        } else {
            this.istoricAlergii = istoricAlergii;
        }
        this.fisaMedicala=new FisaMedicala();
        this.parola=parola;
    }

    public int getIdPacient() {
        return idPacient;
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

    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }

    @Override
    public String toString() {
        return "Pacient: " + getNume() + " " + getPrenume() +
                " | CNP: " + getCnp() +
                " | Grupa: " + grupaSanguina +
                " | Alergii: " + (istoricAlergii.isEmpty() ? "Niciuna" : istoricAlergii);
    }
}
