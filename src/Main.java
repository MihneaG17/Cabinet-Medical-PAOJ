public class Main {
    public static void main(String[] args) {
        CabinetService service=new CabinetService();
        MeniuInteractiv meniu=new MeniuInteractiv(service);

        meniu.ruleaza();
    }
}