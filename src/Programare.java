import java.time.LocalDateTime;

public class Programare {
    public static final String STATUS_IN_ASTEPTARE = "In asteptare";
    public static final String STATUS_FINALIZATA = "Finalizata";
    public static final String STATUS_ANULATA = "Anulata";
    public static final String STATUS_CONFIRMATA = "Confirmata";

    private Pacient pacient;
    private Medic medic;
    private ServiciuMedical serviciu;
    private Sala sala;
    private LocalDateTime dataSiOra;
    private String status;

    public Programare(Pacient pacient, Medic medic, ServiciuMedical serviciu, Sala sala, LocalDateTime dataSiOra) {
        this.pacient=pacient;
        this.medic=medic;
        this.serviciu=serviciu;
        this.sala=sala;
        this.dataSiOra=dataSiOra;
        this.status=STATUS_IN_ASTEPTARE;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public ServiciuMedical getServiciu() {
        return serviciu;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getDataSiOra() {
        return dataSiOra;
    }

    public void setDataSiOra(LocalDateTime dataSiOra) {
        this.dataSiOra = dataSiOra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    @Override
    public String toString() {
        return "Programare: " + dataSiOra.toLocalDate() + " | Ora: " + dataSiOra.toLocalTime() +
                " | Pacient: " + pacient.getNume() + " " + pacient.getPrenume() +
                " | Medic: Dr. " + medic.getNume() + " | Status: " + status;
    }
}
