import java.util.ArrayList;
import java.util.List;

public class FisaMedicala {
    private List<Programare> istoricProgramari;
    private List<Reteta> retetePrescrise;

    public FisaMedicala() {
        this.istoricProgramari=new ArrayList<>();
        this.retetePrescrise=new ArrayList<>();
    }

    public List<Programare> getIstoricProgramari() {
        return istoricProgramari;
    }

    public List<Reteta> getRetetePrescrise() {
        return retetePrescrise;
    }

    public void adaugaProgramare(Programare programare) {
        this.istoricProgramari.add(programare);
    }

    public void adaugaReteta(Reteta reteta) {
        this.retetePrescrise.add(reteta);
    }
}

