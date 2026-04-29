import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeniuInteractiv {
    private CabinetService service;
    private Scanner scanner;

    public MeniuInteractiv(CabinetService service) {
        this.service=service;
        this.scanner=new Scanner(System.in);
    }

    public void ruleaza() {
        int choice=-1;
        do {
            System.out.println("--MENIU PRINCIPAL--");
            System.out.println("1. Logare ca administrator");
            System.out.println("2. Logare ca pacient");
            System.out.println("3. Logare ca medic");
            System.out.println("0. Iesire");
            System.out.println("Alegeti: ");

            if(scanner.hasNextInt()) {
                choice=scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1:
                    autentificareAdmin();
                    break;
                case 2:
                    autentificarePacient();
                    break;
                case 3:
                    autentificareMedic();
                    break;
                case 0:
                    System.out.println("Deconectare");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    private void autentificareAdmin() {
        System.out.println("Introduceti username-ul si parola pentru admin");
        System.out.println("Username: ");
        String userInput=scanner.nextLine();

        System.out.println("Parola: ");
        String parolaInput=scanner.nextLine();

        if(userInput.equals("mihnea") && parolaInput.equals("mihnea")) {
            System.out.println("Autentificare cu succes");
            meniuAdmin();
        }
        else {
            System.out.println("User sau parola incorecte. Acces respins");
        }
    }

    public void meniuAdmin() {
        int choice=-1;
        do {
            System.out.println("1. Gestiune medici");
            System.out.println("2. Gestiune asistenti");
            System.out.println("3. Gestiune pacienti");
            System.out.println("0. Revenire");

            if (scanner.hasNextInt()) {
                choice= scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    gestiuneMedici();
                    break;
                case 2:
                    gestiuneAsistenti();
                    break;
                case 3:
                    gestiunePacienti();
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    //optiunile din meniuAdmin()
    private void gestiuneMedici() {
        int choice=-1;
        do {
            System.out.println("----Gestiune medici----");
            System.out.println("1. Adauga medic nou");
            System.out.println("2. Afiseaza toti medicii");
            System.out.println("3. Concediaza medic");
            System.out.println("0. Inapoi");

            if(scanner.hasNextInt()) {
                choice=scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Adaugare medic nou");
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();

                    System.out.print("Prenume: ");
                    String prenume = scanner.nextLine();

                    System.out.print("CNP: ");
                    String cnp = scanner.nextLine();

                    System.out.print("Telefon: ");
                    String telefon = scanner.nextLine();

                    System.out.print("Salariu: ");
                    double salariu = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Tura (Zi/Noapte): ");
                    String tura = scanner.nextLine();

                    System.out.print("Anul angajarii: ");
                    int an = scanner.nextInt();
                    System.out.print("Luna angajarii (1-12): ");
                    int luna = scanner.nextInt();
                    System.out.print("Ziua angajarii (1-31): ");
                    int zi = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Specializare: ");
                    String specializare = scanner.nextLine();

                    System.out.print("Cost consultatie: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Cod parafa: ");
                    String parafa = scanner.nextLine();

                    LocalDate dataAngajarii = LocalDate.of(an, luna, zi);
                    Medic medicNou = new Medic(nume, prenume, cnp, telefon, salariu, tura, dataAngajarii, specializare, cost, parafa);

                    service.adaugaMedic(medicNou);
                    System.out.println("Medic nou adaugat cu succes");
                    break;
                case 2:
                    System.out.println("Lista medici:");
                    List<Medic> listaMedici=service.getMedici();

                    if(listaMedici.isEmpty()) {
                        System.out.println("Nu sunt medici inregistrati in sistem");
                    }
                    else {
                        for(Medic m : listaMedici) {
                            System.out.println(m);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Concediere medic");
                    System.out.println("Introduceti CNP-ul medicului pe care doriti sa-l concediati");
                    String cnpCautat=scanner.nextLine();

                    boolean concediat=service.concediazaMedic(cnpCautat);
                    if(concediat) {
                        System.out.println("Medicul a fost concediat");
                    }
                    else {
                        System.out.println("Nu s-a gasit medicul cu cnp-ul introdus");
                    }
                    break;
                case 0:
                    System.out.println("Revenire...");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        } while(choice!=0);
    }

    private void gestiuneAsistenti() {
        int choice = -1;
        do {
            System.out.println("----Gestiune asistenti----");
            System.out.println("1. Adauga asistent nou");
            System.out.println("2. Afiseaza toti asistentii");
            System.out.println("3. Concediaza asistent");
            System.out.println("0. Inapoi");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Adaugare asistent");
                    System.out.print("Nume: ");
                    String n = scanner.nextLine();

                    System.out.print("Prenume: ");
                    String p = scanner.nextLine();

                    System.out.print("CNP: ");
                    String cnpAsistent = scanner.nextLine();

                    System.out.print("Telefon: ");
                    String tel = scanner.nextLine();

                    System.out.print("Salariu: ");
                    double sal = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Tura: ");
                    String turaAs = scanner.nextLine();

                    System.out.print("An angajare: ");
                    int anAsistent = scanner.nextInt();

                    System.out.print("Luna: ");
                    int lunaAsistent = scanner.nextInt();

                    System.out.print("Zi: ");
                    int ziAsistent = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Grad profesional: ");
                    String grad = scanner.nextLine();

                    System.out.print("Poate recolta probe? (1-Da / 2-Nu): ");
                    int rec = scanner.nextInt();
                    scanner.nextLine();

                    boolean poateRecolta = (rec == 1);

                    Asistent asistentNou = new Asistent(n, p, cnpAsistent, tel, sal, turaAs,
                            LocalDate.of(anAsistent, lunaAsistent, ziAsistent), grad, poateRecolta);
                    service.adaugaAsistent(asistentNou);

                    break;
                case 2:
                    System.out.println("Lista asistenti");
                    List<Asistent> asistenti=service.getAsistenti();

                    if(asistenti.isEmpty())
                    {
                        System.out.println("Nu e niciun asistent inregistrat in sistem");
                    }
                    else {
                        for(Asistent a : asistenti) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Concediere asistent");
                    System.out.println("Introduceti cnp-ul asistentului");
                    String cnpCautatAsistent=scanner.nextLine();

                    if(service.concediazaAsistent(cnpCautatAsistent)) {
                        System.out.println("Asistentul a fost concediat");
                    }
                    else {
                        System.out.println("Nu s-a gasit niciun angajat cu acest cnp");
                    }
                    break;
                case 0:
                    System.out.println("Revenire...");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        } while (choice != 0);
    }

    private void gestiunePacienti() {
        int choice = -1;
        do {
            System.out.println("----Gestiune pacienti----");
            System.out.println("1. Inregistrare pacient nou");
            System.out.println("2. Cauta pacient dupa cnp");
            System.out.println("0. Inapoi");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Inregistrare pacient");
                    System.out.print("Nume: ");
                    String numP = scanner.nextLine();

                    System.out.print("Prenume: ");
                    String preP = scanner.nextLine();

                    System.out.print("CNP: ");
                    String cnpP = scanner.nextLine();

                    System.out.print("Telefon: ");
                    String telP = scanner.nextLine();

                    System.out.print("Grupa sanguina: ");
                    String grupa = scanner.nextLine();

                    List<String> alergii = new ArrayList<>();
                    System.out.println("Introduceti alergiile (scrie 'gata' cand termini):");
                    while (true) {
                        String alergie = scanner.nextLine();
                        if (alergie.equalsIgnoreCase("gata"))
                            break;
                        alergii.add(alergie);
                    }

                    Pacient pacientNou = new Pacient(numP, preP, cnpP, telP, grupa, alergii);
                    service.adaugaPacient(pacientNou);

                    System.out.println("Pacient adaugat cu succes");
                    break;
                case 2:
                    System.out.println("Cautare pacient dupa cnp");
                    System.out.println("Introduceti cnp-ul cautat");
                    String cnpPacientCautat=scanner.nextLine();

                    Pacient gasit=service.cautaPacientDupaCnp(cnpPacientCautat);

                    if(gasit!=null) {
                        System.out.println(gasit);
                    }
                    else {
                        System.out.println("Nu am gasit niciun pacient cu cnp-ul introdus");
                    }
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        } while (choice != 0);
    }

    private void autentificarePacient() {
        System.out.println("----Autentificare pacient----");
        System.out.println("Introduceti CNP-ul dvs");
        String cnpIntrodus= scanner.nextLine();

        Pacient pacientGasit=service.cautaPacientDupaCnp(cnpIntrodus);

        if(pacientGasit!=null) {
            System.out.println("Autentificare cu succes");
            meniuPacient(pacientGasit);
        }
        else
        {
            System.out.println("Acces respins. CNP-ul introdus nu apartine niciunui pacient inregistrat");
        }
    }

    public void meniuPacient(Pacient pacientLogat) {
        int choice=-1;
        do {
            System.out.println("1. Vezi medici");
            System.out.println("2. Vezi fisa medicala");
            System.out.println("3. Fa o programare");
            System.out.println("4. Vezi programarile efectuate");
            System.out.println("5. Anuleaza o programare");
            System.out.println("6. Plateste facturi");
            System.out.println("0. Revenire");

            if (scanner.hasNextInt()) {
                choice= scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    veziMediciDisponibili();
                    break;
                case 2:
                    vizualizareFisaMedicala(pacientLogat);
                    break;
                case 3:
                    creareProgramare(pacientLogat);
                    break;
                case 4:
                    vizualizareProgramari(pacientLogat);
                    break;
                case 5:
                    anuleazaProgramare(pacientLogat);
                    break;
                case 6:
                    platesteFacturi(pacientLogat);
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    //metode meniuPacient

    private void veziMediciDisponibili() {
        System.out.println("----Medici disponibili----");
        List<Medic> medici=service.getMedici();

        if(medici.isEmpty()) {
            System.out.println("Nu exista medici inregistrati in clinica momentan");
        }
        else
        {
            for(Medic m : medici) {
                System.out.println("-" + m.getPrezentarePublica());
            }
        }
    }

    private void vizualizareFisaMedicala(Pacient pacientLogat) {
        System.out.println("Fisa Medicala - " + pacientLogat.getNume() + " " + pacientLogat.getPrenume());
        System.out.println("---------------");
        System.out.println("Grupa sanguina: " + pacientLogat.getGrupaSanguina());
        System.out.println("Alegii cunoscute: " + (pacientLogat.getIstoricAlergii().isEmpty() ? "Niciuna" : pacientLogat.getIstoricAlergii()));

        FisaMedicala fm=pacientLogat.getFisaMedicala();

        System.out.println("----Istoric programari----");
        List<Programare> istoric=fm.getIstoricProgramari();

        if(istoric.isEmpty()) {
            System.out.println("Nu exista programari inregistrate");
        }
        else {
            for(Programare prog : istoric) {
                System.out.println("- [" + prog.getStatus() + "] " + prog.getDataSiOra().toLocalDate() +
                        " | Medic: Dr. " + prog.getMedic().getNume() +
                        " | Serviciu: " + prog.getServiciu().getNumeServiciu());
            }
        }

        System.out.println("----Retete prescrise----");
        List<Reteta> retete=fm.getRetetePrescrise();

        if(retete.isEmpty())
        {
            System.out.println("Nu exista retete eliberate");
        }
        else {
            for(Reteta r : retete) {
                System.out.println(r);
                System.out.println(" Medicamente ");
                for(Medicament m : r.getMedicamente()) {
                    System.out.println(" -" + m);
                }
                System.out.println("----------");
            }
        }
    }

    private void creareProgramare(Pacient pacientLogat) {
        System.out.println("----Creare programare noua----");
        System.out.println("Alegeti medicul: ");
        List<Medic> mediciDisponibili=service.getMedici();
        List<ServiciuMedical> serviciiDisponibile=service.getServicii();
        List<Sala> saliDisponibile=service.getSali();

        if (mediciDisponibili.isEmpty() || serviciiDisponibile.isEmpty() || saliDisponibile.isEmpty()) {
            System.out.println("Ne cerem scuze, momentan clinica nu are toate resursele (medici/servicii/sali) configurate.");
            return;
        }

        for(int i=0;i<mediciDisponibili.size();i++)
        {
            System.out.println((i + 1) + ". " + mediciDisponibili.get(i).getNume() + " - " + mediciDisponibili.get(i).getPrenume() + " - " + mediciDisponibili.get(i).getSpecializare());
        }
        int indexMedic=scanner.nextInt()-1;
        Medic medicAles=mediciDisponibili.get(indexMedic);

        System.out.println("Alegeti serviciul medical:");

        for (int i=0;i<serviciiDisponibile.size();i++) {
            System.out.println((i + 1) + ". " + serviciiDisponibile.get(i).getNumeServiciu() + " (" + serviciiDisponibile.get(i).getPret() + " RON)");
        }
        int indexServiciu = scanner.nextInt() - 1;
        ServiciuMedical serviciuAles = serviciiDisponibile.get(indexServiciu);

        System.out.println("Alegeti sala:");

        for (int i=0;i<saliDisponibile.size();i++) {
            System.out.println((i + 1) + ". Sala " + saliDisponibile.get(i).getNrSala() + " (" + saliDisponibile.get(i).getTipSala() + ")");
        }
        int indexSala = scanner.nextInt() - 1;
        Sala salaAleasa = saliDisponibile.get(indexSala);

        System.out.println("Introduceti data si ora (format: an luna zi ora minut):");
        int anP = scanner.nextInt();
        int lunaP = scanner.nextInt();
        int ziP = scanner.nextInt();
        int oraP = scanner.nextInt();
        int minutP = scanner.nextInt();
        scanner.nextLine();

        java.time.LocalDateTime dataProgramarii = java.time.LocalDateTime.of(anP, lunaP, ziP, oraP, minutP);

        Programare programareNoua=new Programare(pacientLogat, medicAles, serviciuAles, salaAleasa, dataProgramarii);

        service.adaugaProgramare(programareNoua);
        System.out.println("Programare creata cu succes pentru data de: " + dataProgramarii);
    }

    private void vizualizareProgramari(Pacient pacientLogat) {
        System.out.println("----Lista programari----");
        List<Programare> programari = pacientLogat.getFisaMedicala().getIstoricProgramari();

        if(programari.isEmpty())
        {
            System.out.println("Nu s-a efectuat nicio programare");
        }
        else {
            for (Programare prog : programari) {
                System.out.println("- Data: " + prog.getDataSiOra().toLocalDate() +
                        " | Ora: " + prog.getDataSiOra().toLocalTime() +
                        " | Medic: Dr. " + prog.getMedic().getNume() +
                        " | Serviciu: " + prog.getServiciu().getNumeServiciu() +
                        " | Status: [" + prog.getStatus() + "]");
            }
        }
    }
    private void anuleazaProgramare(Pacient pacientLogat) {
        System.out.println("----Anulare programare----");
        List<Programare> programari = pacientLogat.getFisaMedicala().getIstoricProgramari();
        List<Programare> programariAnulabile=new ArrayList<>();

        for(Programare prog : programari) {
            if(prog.getStatus().equals(Programare.STATUS_IN_ASTEPTARE) || prog.getStatus().equals(Programare.STATUS_CONFIRMATA)) {
                programariAnulabile.add(prog);
            }
        }
        if (programariAnulabile.isEmpty())
        {
            System.out.println("Nu aveti nicio programare care poate fi anulata");
            return;
        }
        System.out.println("Alegeti numarul programarii pe care doriti sa o anulati: ");
        for(int i=0;i<programariAnulabile.size();i++) {
            Programare prog=programariAnulabile.get(i);
            System.out.println((i + 1) + ". Data: " + prog.getDataSiOra().toLocalDate() +
                    " | Ora: " + prog.getDataSiOra().toLocalTime() +
                    " | Medic: Dr. " + prog.getMedic().getNume());
        }
        System.out.println("0. Inapoi");

        System.out.println("Alegerea dumneavoastra: ");
        int index=-1;
        if(scanner.hasNextInt()) {
            index= scanner.nextInt();
            scanner.nextLine();
        }

        if(index>0 && index<=programariAnulabile.size()) {
            Programare progDeAnulat=programariAnulabile.get(index-1);
            progDeAnulat.setStatus(Programare.STATUS_ANULATA);
            System.out.println("Programarea a fost anulata cu succes!");
        }
        else
        {
            System.out.println("Optiune invalida");
        }
    }

    private void platesteFacturi(Pacient pacientLogat) {
        System.out.println("----Plata facturi----");

        List<Factura> facturiDePlatit=new ArrayList<>();
        for(Factura f : service.getFacturi()) {
            if(f.getProgramare().getPacient().equals(pacientLogat) && f.getStatusPlata().equals(Factura.STATUS_PLATA_ASTEPTARE)) {
                facturiDePlatit.add(f);
            }
        }
        if(facturiDePlatit.isEmpty())
        {
            System.out.println("Nu mai sunt facturi de platit");
            return;
        }

        System.out.println("Facturile dvs neplatite: ");
        for(int i=0;i<facturiDePlatit.size();i++) {
            Factura f=facturiDePlatit.get(i);
            System.out.println((i + 1) + ". Factura #" + f.getIdFactura() +
                    " | Serviciu: " + f.getProgramare().getServiciu().getNumeServiciu() +
                    " | Suma: " + f.getPret() + " RON" +
                    " | Data emiterii: " + f.getDataEmiterii());
        }
        System.out.println("0. Anuleaza");

        System.out.println("Alegeti numarul facturii pe care doriti sa o platiti: ");
        int alegere=-1;
        if(scanner.hasNextInt()) {
            alegere= scanner.nextInt();
            scanner.nextLine();
        }

        if(alegere>0 && alegere<= facturiDePlatit.size()) {
            Factura facturaAleasa=facturiDePlatit.get(alegere-1);
            System.out.println("Se proceseaza plata pentru suma de " + facturaAleasa.getPret() + " RON aferenta facturii");
            facturaAleasa.setStatusPlata(Factura.STATUS_PLATA_FINALIZATA);
            System.out.println("Plata finalizata");
        }
        else
        {
            System.out.println("Optiune incorecta");
        }
    }
    private void autentificareMedic() {
        System.out.println("----Autentificare medic----");
        System.out.println("Introduceti CNP-ul dvs");
        String cnpIntrodus= scanner.nextLine();

        Medic medicGasit=service.cautaMedicDupaCnp(cnpIntrodus);

        if(medicGasit!=null) {
            System.out.println("Autentificare cu succes");
            meniuMedic(medicGasit);
        }
        else
        {
            System.out.println("Acces respins. CNP-ul introdus nu apartine niciunui medic inregistrat");
        }
    }
    public void meniuMedic(Medic medicLogat) {
        int choice=-1;
        do {
            System.out.println("1. Vezi programarile de azi");
            System.out.println("2. Finalizeaza o consultatie");
            System.out.println("3. Elibereaza o reteta");
            System.out.println("4. Emite factura");
            System.out.println("0. Revenire");

            if (scanner.hasNextInt()) {
                choice= scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Introduceti un numar valid");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                   veziProgramariAzi(medicLogat);
                    break;
                case 2:
                    finalizeazaConsultatie(medicLogat);
                    break;
                case 3:
                    elibereazaReteta(medicLogat);
                    break;
                case 4:
                    emiteFactura(medicLogat);
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    //metode meniuMedic
    private void veziProgramariAzi(Medic medicLogat) {
        System.out.println("----Programarile de astazi----");
        java.time.LocalDate azi=java.time.LocalDate.now();

        List<Programare> programari=service.getToateProgramarile();
        boolean gasit=false;

        for (Programare p : programari) {
            if(p.getMedic().equals(medicLogat) && p.getDataSiOra().toLocalDate().equals(azi)) {
                System.out.println("- Ora: " + p.getDataSiOra().toLocalTime() +
                        " | Pacient: " + p.getPacient().getNume() + " " + p.getPacient().getPrenume() +
                        " | Serviciu: " + p.getServiciu().getNumeServiciu() +
                        " | Status: [" + p.getStatus() + "]");
                gasit=true;
            }
        }
        if(!gasit) {
            System.out.println("Nu s-au gasit programari pentru data de azi, " + azi);
        }
    }

    private void finalizeazaConsultatie(Medic medicLogat) {
        System.out.println("----Finalizare consultatie----");
        java.time.LocalDate azi = java.time.LocalDate.now();
        List<Programare> programari = service.getToateProgramarile();
        List<Programare> activeAzi = new ArrayList<>();

        for(Programare p : programari) {
            if (p.getMedic().equals(medicLogat) && p.getDataSiOra().toLocalDate().equals(azi) &&
                    (p.getStatus().equals(Programare.STATUS_IN_ASTEPTARE) || p.getStatus().equals(Programare.STATUS_CONFIRMATA))) {
                        activeAzi.add(p);
            }
        }

        if(activeAzi.isEmpty()) {
            System.out.println("Nu aveti consultatii active astazi");
            return;
        }
        System.out.println("Alegeti pacientul a carui consultatie s-a incheiat:");
        for (int i = 0; i < activeAzi.size(); i++) {
            Programare p = activeAzi.get(i);
            System.out.println((i + 1) + ". Ora: " + p.getDataSiOra().toLocalTime() +
                    " | Pacient: " + p.getPacient().getNume() + " " + p.getPacient().getPrenume());
        }
        System.out.println("0. Inapoi");

        System.out.print("Alegerea dvs: ");
        int optiune = -1;
        if (scanner.hasNextInt()) {
            optiune = scanner.nextInt();
            scanner.nextLine();
        }

        if (optiune > 0 && optiune <= activeAzi.size()) {
            Programare selectata = activeAzi.get(optiune - 1);
            selectata.setStatus(Programare.STATUS_FINALIZATA);

            System.out.println("Consultatia pentru " + selectata.getPacient().getNume() + " a fost marcata ca finalizata");
        } else {
            System.out.println("Optiune invalida");
        }
    }

    private void elibereazaReteta(Medic medicLogat) {
        System.out.println("---Eliberare reteta---");

        java.time.LocalDate azi=java.time.LocalDate.now();
        List<Programare> programari= service.getToateProgramarile();
        List<Programare> consultatiiIncheiate = new ArrayList<>();

        for (Programare p : programari) {
            if (p.getMedic().equals(medicLogat) && p.getDataSiOra().toLocalDate().equals(azi) && p.getStatus().equals(Programare.STATUS_FINALIZATA)) {
                consultatiiIncheiate.add(p);
            }
        }
        if (consultatiiIncheiate.isEmpty()) {
            System.out.println("Nu aveti nicio consultatie finalizata astazi pentru care sa puteti elibera o reteta.");
            return;
        }

        System.out.println("Selectati pacientul pentru reteta:");
        for (int i=0;i<consultatiiIncheiate.size(); i++) {
            Pacient pac=consultatiiIncheiate.get(i).getPacient();
            System.out.println((i+1) + ". " + pac.getNume() + " " + pac.getPrenume());
        }

        System.out.print("Alegerea dumneavoastra: ");
        int optiune=scanner.nextInt();
        scanner.nextLine();

        if (optiune<=0 || optiune>consultatiiIncheiate.size()) {
            System.out.println("Optiune invalida");
            return;
        }

        Pacient pacientAles=consultatiiIncheiate.get(optiune-1).getPacient();
        Reteta retetaNoua=new Reteta(medicLogat, azi);
        System.out.println("\nIntroduceti medicamentele (scrie 'gata' la nume pentru a termina):");

        while(true) {
            System.out.print("Nume medicament: ");
            String numeM = scanner.nextLine();
            if (numeM.equalsIgnoreCase("gata"))
                break;

            System.out.print("Dozaj (mg): ");
            int dozaj=scanner.nextInt();
            scanner.nextLine();

            System.out.print("Substanta activa: ");
            String substanta=scanner.nextLine();

            Medicament m=new Medicament(numeM, dozaj, substanta);
            retetaNoua.addMedicament(m);
            System.out.println("-> Adaugat.");
        }
        if (!retetaNoua.getMedicamente().isEmpty()) {
            pacientAles.getFisaMedicala().adaugaReteta(retetaNoua);
            System.out.println("Reteta a fost salvata cu succes in fisa pacientului " + pacientAles.getNume());
        } else {
            System.out.println("⚠Reteta nu a fost creata deoarece nu ati adaugat niciun medicament.");
        }
    }
    private void emiteFactura(Medic medicLogat) {
        System.out.println("----Emitere factura----");

        java.time.LocalDate azi=java.time.LocalDate.now();
        List<Programare> programari=service.getToateProgramarile();

        List<Factura> toateFacturile=service.getFacturi();
        List<Programare> programariEligibile=new ArrayList<>();

        for (Programare p : programari) {
            if (p.getMedic().equals(medicLogat) && p.getDataSiOra().toLocalDate().equals(azi) && p.getStatus().equals(Programare.STATUS_FINALIZATA)) {

                boolean areFactura = false;
                for (Factura f : toateFacturile) {
                    if (f.getProgramare().equals(p)) {
                        areFactura = true;
                        break;
                    }
                }

                if (!areFactura) {
                    programariEligibile.add(p);
                }
            }
        }

        if (programariEligibile.isEmpty()) {
            System.out.println("Nu există nicio consultatie finalizata astazi care să necesite emiterea unei facturi.");
            return;
        }

        System.out.println("Selectați pacientul pentru care emiteti factura:");
        for (int i=0;i<programariEligibile.size();i++) {
            Programare p=programariEligibile.get(i);
            System.out.println((i+1) + ". Pacient: " + p.getPacient().getNume() + " " + p.getPacient().getPrenume() +
                    " | Serviciu: " + p.getServiciu().getNumeServiciu());
        }

        System.out.print("Alegerea dumneavoastra: ");
        int optiune=-1;
        if (scanner.hasNextInt()) {
            optiune=scanner.nextInt();
            scanner.nextLine();
        }

        if (optiune>0 && optiune<=programariEligibile.size()) {
            Programare selectata=programariEligibile.get(optiune-1);

            double pretTotal=selectata.getServiciu().getPret() + medicLogat.getCostConsultatie();

            Factura facturaNoua=new Factura(pretTotal, azi, selectata);
            service.adaugaFactura(facturaNoua);

            System.out.println("Factura #" + facturaNoua.getIdFactura() + " in valoare de " + pretTotal +
                    " RON a fost emisa cu succes pentru pacientul " + selectata.getPacient().getNume());
        } else {
            System.out.println("Optiune invalida");
        }
    }
}
