public class Medicament {
    private String nume;
    private int dozaj;
    private String substantaActiva;

    public Medicament(String nume, int dozaj, String substantaActiva) {
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
