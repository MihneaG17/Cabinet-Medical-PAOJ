package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reteta {
    private int idReteta;
    private Medic medic;
    private Pacient pacient;
    private LocalDate dataEmiterii;
    private List<Medicament> medicamente;

    public Reteta(Medic medic, Pacient pacient, LocalDate dataEmiterii) {
        this.idReteta=0;
        this.medic=medic;
        this.pacient=pacient;
        this.dataEmiterii=dataEmiterii;
        this.medicamente=new ArrayList<>();
    }

    //constructor pentru citirea din baza de date
    public Reteta(int idReteta, Medic medic, Pacient pacient, LocalDate dataEmiterii) {
        this.idReteta=idReteta;
        this.medic=medic;
        this.pacient=pacient;
        this.dataEmiterii=dataEmiterii;
        this.medicamente=new ArrayList<>();
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public List<Medicament> getMedicamente() {
        return medicamente;
    }

    public LocalDate getDataEmiterii() {
        return dataEmiterii;
    }

    public void addMedicament(Medicament medicament) {
        this.medicamente.add(medicament);
    }

    @Override
    public String toString() {
        return "Reteta #" + idReteta + " | Data: " + dataEmiterii +
                " | Pacient: " + pacient.getNume() + " " + pacient.getPrenume() +
                " | Prescris de Dr. " + medic.getNume() + " " + medic.getPrenume();
    }
}
