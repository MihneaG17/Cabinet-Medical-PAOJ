package models;

public class Medicament {
    private int idMedicament;
    private String nume;
    private int dozaj;
    private String substantaActiva;

    public Medicament(String nume, int dozaj, String substantaActiva) {
        this.idMedicament=0;
        this.nume=nume;
        this.dozaj=dozaj;
        this.substantaActiva=substantaActiva;
    }

    //constructor pentru citirea din baza de date
    public Medicament(int idMedicament, String nume, int dozaj, String substantaActiva) {
        this.idMedicament=idMedicament;
        this.nume=nume;
        this.dozaj=dozaj;
        this.substantaActiva=substantaActiva;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getDozaj() {
        return dozaj;
    }

    public void setDozaj(int dozaj) {
        this.dozaj = dozaj;
    }

    public String getSubstantaActiva() {
        return substantaActiva;
    }

    public void setSubstantaActiva(String substantaActiva) {
        this.substantaActiva = substantaActiva;
    }

    @Override
    public String toString() {
        return nume + " (" + substantaActiva + " ) " + " - " + dozaj + " mg";
    }
}
