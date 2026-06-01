package models;

import java.time.LocalDate;

public class Asistent extends PersonalMedical {
    private int idAsistent;
    private String gradProfesional;
    private boolean poateRecolta;

    public Asistent(String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String gradProfesional, boolean poateRecolta) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
        this.idAsistent=0;
        this.gradProfesional=gradProfesional;
        this.poateRecolta=poateRecolta;
    }

    //constructor pentru baza de date
    public Asistent(int idAsistent, String nume, String prenume, String cnp, String nrTelefon, double salariu, String tura, LocalDate dataAngajarii, String gradProfesional, boolean poateRecolta) {
        super(nume, prenume, cnp, nrTelefon, salariu, tura, dataAngajarii);
        this.idAsistent=idAsistent;
        this.gradProfesional=gradProfesional;
        this.poateRecolta=poateRecolta;
    }

    public int getIdAsistent() {
        return idAsistent;
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

    @Override
    public String toString() {
        return "models.Asistent: " + getNume() + " " + getPrenume() +
                " | Nr. Telefon: " + getNrTelefon() +
                " | Grad: " + gradProfesional +
                " | Salariu: " + getSalariu() +
                " | Recoltare: " + (poateRecolta ? "Da" : "Nu") +
                " | Tura: " + getTura();
    }
}
