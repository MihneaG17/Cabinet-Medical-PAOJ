public class ServiciuMedical {
    private int idServiciu;
    private String numeServiciu;
    private double pret;
    private int durataMinute;

    public ServiciuMedical(String numeServiciu, double pret, int durataMinute) {
        this.idServiciu=0;
        this.numeServiciu=numeServiciu;
        this.pret=pret;
        this.durataMinute=durataMinute;
    }

    public ServiciuMedical(int idServiciu, String numeServiciu, double pret, int durataMinute) {
        this.idServiciu=idServiciu;
        this.numeServiciu=numeServiciu;
        this.pret=pret;
        this.durataMinute=durataMinute;
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public String getNumeServiciu() {
        return numeServiciu;
    }

    public void setNumeServiciu(String numeServiciu) {
        this.numeServiciu = numeServiciu;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getDurataMinute() {
        return durataMinute;
    }

    public void setDurataMinute(int durataMinute) {
        this.durataMinute = durataMinute;
    }
}
