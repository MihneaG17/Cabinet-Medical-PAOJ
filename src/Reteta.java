import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reteta {
    private Medic medic;
    private List<Medicament> medicamente;
    private LocalDate dataEmiterii;

    public Reteta(Medic medic, LocalDate dataEmiterii) {
        this.medic=medic;
        this.dataEmiterii=dataEmiterii;
        this.medicamente=new ArrayList<>();
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
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
        return "Data: " + dataEmiterii + " - Prescris de Dr. " + medic.getNume() + " " + medic.getPrenume();
    }
}
