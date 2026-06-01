package models;

import java.time.LocalDateTime;

public class Programare {
    public static final String STATUS_IN_ASTEPTARE = "In asteptare";
    public static final String STATUS_FINALIZATA = "Finalizata";
    public static final String STATUS_ANULATA = "Anulata";
    public static final String STATUS_CONFIRMATA = "Confirmata";

    private int idProgramare;
    private Pacient pacient;
    private Medic medic;
    private Asistent asistent;
    private ServiciuMedical serviciu;
    private Sala sala;
    private LocalDateTime dataSiOra;
    private String status;

    //constructor fara asistent
    public Programare(Pacient pacient, Medic medic, ServiciuMedical serviciu, Sala sala, LocalDateTime dataSiOra) {
        this.idProgramare=0;
        this.pacient=pacient;
        this.medic=medic;
        this.asistent=null;
        this.serviciu=serviciu;
        this.sala=sala;
        this.dataSiOra=dataSiOra;
        this.status=STATUS_IN_ASTEPTARE;
    }

    //constructor cu asistent
    public Programare(Pacient pacient, Medic medic, Asistent asistent, ServiciuMedical serviciu, Sala sala, LocalDateTime dataSiOra) {
        this.idProgramare=0;
        this.pacient=pacient;
        this.medic=medic;
        this.asistent=asistent;
        this.serviciu=serviciu;
        this.sala=sala;
        this.dataSiOra=dataSiOra;
        this.status=STATUS_IN_ASTEPTARE;
    }

    //constructor pentru cand citesc din baza de date
    public Programare(int idProgramare, Pacient pacient, Medic medic, Asistent asistent,ServiciuMedical serviciu, Sala sala, LocalDateTime dataSiOra) {
        this.idProgramare=idProgramare;
        this.pacient=pacient;
        this.medic=medic;
        this.asistent=asistent;
        this.serviciu=serviciu;
        this.sala=sala;
        this.dataSiOra=dataSiOra;
        this.status=STATUS_IN_ASTEPTARE;
    }

    public int getIdProgramare() {
        return idProgramare;
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

    public Asistent getAsistent() {
        return asistent;
    }

    public void setAsistent(Asistent asistent) {
        this.asistent = asistent;
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
        return "models.Programare: " + dataSiOra.toLocalDate() + " | Ora: " + dataSiOra.toLocalTime() +
                " | models.Pacient: " + pacient.getNume() + " " + pacient.getPrenume() +
                " | models.Medic: Dr. " + medic.getNume() + " | Status: " + status;
    }
}
