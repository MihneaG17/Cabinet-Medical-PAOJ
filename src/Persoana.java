public abstract class Persoana {
    protected String nume;
    protected String prenume;
    protected String cnp;
    protected String nrTelefon;

    public Persoana(String nume, String prenume, String cnp, String nrTelefon) {
        this.nume=nume;
        this.prenume=prenume;
        this.cnp=cnp;
        this.nrTelefon=nrTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }
}
