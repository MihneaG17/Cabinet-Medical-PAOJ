import java.time.LocalDate;

public class Factura {
    private static int contorIdFactura=1;

    public static final String STATUS_PLATA_ASTEPTARE = "In asteptare";
    public static final String STATUS_PLATA_FINALIZATA= "Finalizata";

    private double pret;
    private LocalDate dataEmiterii;
    private String statusPlata;
    private Programare programare;
    private int idFactura;

    public Factura(double pret, LocalDate dataEmiterii, Programare programare) {
        this.idFactura=contorIdFactura++;
        this.pret=pret;
        this.dataEmiterii=dataEmiterii;
        this.programare=programare;
        this.statusPlata=STATUS_PLATA_ASTEPTARE;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public LocalDate getDataEmiterii() {
        return dataEmiterii;
    }

    public String getStatusPlata() {
        return statusPlata;
    }

    public void setStatusPlata(String statusPlata) {
        this.statusPlata = statusPlata;
    }

    public Programare getProgramare() {
        return programare;
    }

    public int getIdFactura() {
        return idFactura;
    }
}
