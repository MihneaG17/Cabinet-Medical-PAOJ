public class Sala {
    private int nrSala;
    private int etaj;
    private String tipSala;

    public Sala(int nrSala, int etaj, String tipSala) {
        this.nrSala=nrSala;
        this.etaj=etaj;
        this.tipSala=tipSala;
    }

    public int getNrSala() {
        return nrSala;
    }

    public void setNrSala(int nrSala) {
        this.nrSala = nrSala;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }

    public String getTipSala() {
        return tipSala;
    }

    public void setTipSala(String tipSala) {
        this.tipSala = tipSala;
    }
}
