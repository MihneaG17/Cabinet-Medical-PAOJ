package ui;

import db.DatabaseManager;
import models.*;
import service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeniuInteractiv {
    private Scanner scanner;

    public MeniuInteractiv() {
        this.scanner=new Scanner(System.in);
    }

    public void ruleaza() {
        int choice=-1;
        do {
            System.out.println("--MENIU PRINCIPAL--");
            System.out.println("1. Logare ca administrator");
            System.out.println("2. Logare ca pacient");
            System.out.println("3. Logare ca medic");
            System.out.println("4. Creare cont pacient nou");
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
                case 4:
                    inregistrareContPacient();
                    break;
                case 0:
                    System.out.println("Deconectare");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    //inregistrare cont pacient
    private void inregistrareContPacient() {
        System.out.println("---- Creare cont nou pacient ----");
        System.out.print("Nume: ");
        String numP = scanner.nextLine();

        System.out.print("Prenume: ");
        String preP = scanner.nextLine();

        System.out.print("CNP: ");
        String cnpP = scanner.nextLine();

        Pacient existent = PacientService.getInstance().cautaDupaCnp(cnpP);
        if (existent != null) {
            System.out.println("Exista deja un cont inregistrat cu acest CNP. Va rugam sa va logati sau sa folositi alt CNP");
            return;
        }

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

        System.out.print("Alegeti o parola pentru cont: ");
        String parolaP = scanner.nextLine();

        Pacient pacientNou = new Pacient(numP, preP, cnpP, telP, grupa, alergii, parolaP);
        PacientService.getInstance().create(pacientNou);

        System.out.println("Contul a fost creat cu succes!");
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
            System.out.println("4. Vizualizare resurse clinica");
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
                case 4:
                    vizualizareResurse();
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

                    System.out.print("Parola: ");
                    String parolaM = scanner.nextLine();

                    Medic medicNou = new Medic(nume, prenume, cnp, telefon, salariu, tura, dataAngajarii, specializare, cost, parafa, parolaM);

                    MedicService.getInstance().create(medicNou);
                    break;
                case 2:
                    System.out.println("Lista medici:");
                    List<Medic> listaMedici=MedicService.getInstance().readAll();

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

                    Medic medicGasit = MedicService.getInstance().cautaDupaCnp(cnpCautat);
                    if (medicGasit != null) {
                        MedicService.getInstance().delete(medicGasit.getIdMedic());
                        System.out.println("Medicul Dr. " + medicGasit.getNume() + " a fost concediat cu succes.");
                    } else {
                        System.out.println("Eroare: Nu s-a gasit niciun medic angajat cu CNP-ul introdus.");
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

                    System.out.print("Tura (Zi/Noapte): ");
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

                    AsistentService.getInstance().create(asistentNou);

                    break;
                case 2:
                    System.out.println("Lista asistenti");
                    List<Asistent> asistenti=AsistentService.getInstance().readAll();

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
                    System.out.println("Introduceti CNP-ul asistentului:");
                    String cnpCautatAsistent = scanner.nextLine();

                    Asistent asistentGasit = AsistentService.getInstance().cautaDupaCnp(cnpCautatAsistent);

                    if (asistentGasit != null) {
                        AsistentService.getInstance().delete(asistentGasit.getIdAsistent());
                        System.out.println("Asistentul " + asistentGasit.getNume() + " " + asistentGasit.getPrenume() + " a fost concediat cu succes.");
                    } else {
                        System.out.println("Nu s-a gasit niciun asistent cu acest CNP.");
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
            System.out.println("3. Afiseaza toti pacientii");
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
                    inregistrareContPacient();
                    break;
                case 2:
                    System.out.println("Cautare pacient dupa CNP");
                    System.out.println("Introduceti CNP-ul cautat:");
                    String cnpPacientCautat = scanner.nextLine();

                    Pacient gasit = PacientService.getInstance().cautaDupaCnp(cnpPacientCautat);

                    if (gasit != null) {
                        System.out.println(gasit);
                    } else {
                        System.out.println("Nu am gasit niciun pacient cu CNP-ul introdus.");
                    }
                    break;
                case 3:
                    System.out.println("Lista tuturor pacientilor");
                    List<Pacient> listaPacienti = PacientService.getInstance().readAll();

                    if (listaPacienti.isEmpty()) {
                        System.out.println("Nu exista pacienti inregistrati in baza de date.");
                    } else {
                        for (Pacient p : listaPacienti) {
                            System.out.println(p);
                        }
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

    private void vizualizareResurse() {
        int choice = -1;
        do {
            System.out.println("----Vizualizare resurse clinica----");
            System.out.println("1. Afiseaza toate salile");
            System.out.println("2. Afiseaza serviciile medicale");
            System.out.println("3. Afiseaza catalogul de medicamente");
            System.out.println("0. Inapoi");
            System.out.print("Alegeti: ");

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
                    System.out.println("--- Lista Sali ---");
                    List<models.Sala> sali = SalaService.getInstance().readAll();
                    if(sali.isEmpty()) {
                        System.out.println("Nu exista sali inregistrate.");
                    } else {
                        for(models.Sala s : sali) {
                            System.out.println("Sala " + s.getNrSala() + " | Etaj: " + s.getEtaj() + " | Destinatie: " + s.getTipSala());
                        }
                    }
                    System.out.println("------------------");
                    break;

                case 2:
                    System.out.println("--- Servicii Medicale ---");
                    List<models.ServiciuMedical> servicii = ServiciuMedicalService.getInstance().readAll();
                    if(servicii.isEmpty()) {
                        System.out.println("Nu exista servicii inregistrate.");
                    } else {
                        for(models.ServiciuMedical sm : servicii) {
                            System.out.println("ID: " + sm.getIdServiciu() + " | " + sm.getNumeServiciu() +
                                    " | Durata: " + sm.getDurataMinute() + " min | Pret: " + sm.getPret() + " RON");
                        }
                    }
                    System.out.println("-------------------------");
                    break;

                case 3:
                    System.out.println("--- Catalog Medicamente ---");
                    List<models.Medicament> medicamente = MedicamentService.getInstance().readAll();
                    if(medicamente.isEmpty()) {
                        System.out.println("Nu exista medicamente inregistrate.");
                    } else {
                        for(models.Medicament m : medicamente) {
                            System.out.println("ID: " + m.getIdMedicament() + " | " + m.getNume() +
                                    " " + m.getDozaj() + "mg | Substanta activa: " + m.getSubstantaActiva());
                        }
                    }
                    System.out.println("---------------------------");
                    break;

                case 0:
                    System.out.println("Revenire la meniul administratorului...");
                    break;

                default:
                    System.out.println("Optiune invalida");
            }
        } while (choice != 0);
    }

    private void autentificarePacient() {
        System.out.println("----Autentificare pacient----");
        System.out.println("Introduceti CNP-ul dvs: ");
        String cnpIntrodus= scanner.nextLine();

        Pacient pacientGasit=PacientService.getInstance().cautaDupaCnp(cnpIntrodus);

        if(pacientGasit!=null) {

            System.out.println("Introduceti parola: ");
            String parolaPacient=scanner.nextLine();

            if(pacientGasit.getParola().equals(parolaPacient))
            {
                System.out.println("Autentificare cu succes!");
                meniuPacient(pacientGasit);
            }
            else {
                System.out.println("Parola introdusa este incorecta. Incercati sa va logati din nou");
            }
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
            System.out.println("7. Vezi istoricul tuturor facturilor");
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
                case 7:
                    istoricFacturiComplete(pacientLogat);
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
        System.out.println("Medici disponibili");
        List<Medic> medici=MedicService.getInstance().readAll();

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
        System.out.println("Alegii cunoscute: " + (pacientLogat.getIstoricAlergii().isEmpty() ? "Niciuna" : String.join(", ", pacientLogat.getIstoricAlergii())));

        System.out.println("----Istoric programari----");
        List<Programare> istoric = new ArrayList<>();
        for (Programare p : ProgramareService.getInstance().readAll()) {
            if (p.getPacient().getIdPacient() == pacientLogat.getIdPacient()) {
                istoric.add(p);
            }
        }

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
        List<Reteta> retete = new ArrayList<>();
        for (Reteta r : RetetaService.getInstance().readAll()) {
            if (r.getPacient().getIdPacient() == pacientLogat.getIdPacient()) {
                retete.add(r);
            }
        }

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

        List<Medic> mediciDisponibili = MedicService.getInstance().readAll();
        List<ServiciuMedical> serviciiDisponibile = ServiciuMedicalService.getInstance().readAll();
        List<Sala> saliDisponibile = SalaService.getInstance().readAll();

        if (mediciDisponibili.isEmpty() || serviciiDisponibile.isEmpty() || saliDisponibile.isEmpty()) {
            System.out.println("Ne cerem scuze, momentan clinica nu are toate resursele (medici/servicii/sali) configurate.");
            return;
        }

        System.out.println("Alegeti serviciul medical:");
        for (int i=0; i<serviciiDisponibile.size(); i++) {
            System.out.println((i + 1) + ". " + serviciiDisponibile.get(i).getNumeServiciu() + " (" + serviciiDisponibile.get(i).getPret() + " RON)");
        }
        int indexServiciu = scanner.nextInt() - 1;
        ServiciuMedical serviciuAles = serviciiDisponibile.get(indexServiciu);

        List<Medic> mediciFiltrati = new ArrayList<>();
        for (Medic m : mediciDisponibili) {
            if (serviciuAles.getNumeServiciu().toLowerCase().contains(m.getSpecializare().toLowerCase()) ||
                m.getSpecializare().toLowerCase().contains(serviciuAles.getNumeServiciu().toLowerCase())) {
                mediciFiltrati.add(m);
            }
        }

        if (mediciFiltrati.isEmpty()) {
            System.out.println("Nu exista medici cu specializarea corespunzatoare acestui serviciu.");
            return;
        }

        System.out.println("Alegeti medicul: ");
        for(int i=0; i<mediciFiltrati.size(); i++) {
            System.out.println((i + 1) + ". Dr. " + mediciFiltrati.get(i).getNume() + " " + mediciFiltrati.get(i).getPrenume() + " - " + mediciFiltrati.get(i).getSpecializare());
        }
        int indexMedic = scanner.nextInt() - 1;
        Medic medicAles = mediciFiltrati.get(indexMedic);

        System.out.println("Introduceti data dorita pentru programare:");
        System.out.print("An: ");
        int anP = scanner.nextInt();
        System.out.print("Luna (1-12): ");
        int lunaP = scanner.nextInt();
        System.out.print("Zi (1-31): ");
        int ziP = scanner.nextInt();
        scanner.nextLine();

        java.time.LocalDate dataAleasa;
        try {
            dataAleasa = java.time.LocalDate.of(anP, lunaP, ziP);
        } catch (Exception e) {
            System.out.println("Data introdusa nu este valida.");
            return;
        }

        List<Integer> oreDisponibile = new ArrayList<>(java.util.Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16));
        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();

        for (Programare p : toateProgramarile) {
            if (p.getMedic().getIdMedic() == medicAles.getIdMedic() && 
                p.getDataSiOra().toLocalDate().equals(dataAleasa) &&
                !p.getStatus().equals(Programare.STATUS_ANULATA)) {
                oreDisponibile.remove(Integer.valueOf(p.getDataSiOra().getHour()));
            }
        }

        if (oreDisponibile.isEmpty()) {
            System.out.println("Medicul selectat nu are nicio ora disponibila in data de " + dataAleasa + ".");
            return;
        }

        System.out.println("Ore disponibile pentru data de " + dataAleasa + ":");
        for (int i = 0; i < oreDisponibile.size(); i++) {
            System.out.println((i + 1) + ". Ora " + oreDisponibile.get(i) + ":00");
        }
        System.out.print("Alegeti ora: ");
        int indexOra = scanner.nextInt() - 1;
        int oraAleasa = oreDisponibile.get(indexOra);
        scanner.nextLine();

        java.time.LocalDateTime dataFinala = java.time.LocalDateTime.of(anP, lunaP, ziP, oraAleasa, 0);

        Sala salaAleasa = null;
        for (Sala s : saliDisponibile) {
            boolean salaOcupata = false;
            for (Programare p : toateProgramarile) {
                if (p.getSala().getNrSala() == s.getNrSala() && 
                    p.getDataSiOra().equals(dataFinala) &&
                    !p.getStatus().equals(Programare.STATUS_ANULATA)) {
                    salaOcupata = true;
                    break;
                }
            }
            if (!salaOcupata) {
                salaAleasa = s;
                break;
            }
        }

        if (salaAleasa == null) {
            System.out.println("Nu exista nicio sala disponibila la data si ora selectata.");
            return;
        }

        Programare programareNoua = new Programare(pacientLogat, medicAles, serviciuAles, salaAleasa, dataFinala);

        ProgramareService.getInstance().create(programareNoua);
        System.out.println("Programare creata cu succes pentru data de: " + dataFinala + ", alocata in Sala " + salaAleasa.getNrSala() + ".");
    }

    private void istoricFacturiComplete(Pacient pacientLogat) {
        System.out.println("----Istoric complet facturi----");
        List<Factura> toateFacturile = FacturaService.getInstance().readAll();
        boolean gasit = false;

        for (Factura f : toateFacturile) {
            if (f.getProgramare().getPacient().getIdPacient() == pacientLogat.getIdPacient()) {
                System.out.println("- Factura #" + f.getIdFactura() +
                        " | Serviciu: " + f.getProgramare().getServiciu().getNumeServiciu() +
                        " | Suma: " + f.getPret() + " RON" +
                        " | Status Plata: " + f.getStatusPlata() +
                        " | Data emiterii: " + f.getDataEmiterii());
                gasit = true;
            }
        }
        
        if (!gasit) {
            System.out.println("Nu aveti nicio factura inregistrata.");
        }
    }

    private void vizualizareProgramari(Pacient pacientLogat) {
        System.out.println("----Lista programari----");
        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();

        List<Programare> programarileMele = new ArrayList<>();
        for (Programare prog : toateProgramarile) {
            if (prog.getPacient().getIdPacient() == pacientLogat.getIdPacient()) {
                programarileMele.add(prog);
            }
        }

        if(programarileMele.isEmpty())
        {
            System.out.println("Nu aveti nicio programare inregistrata");
        }
        else {
            for (Programare prog : programarileMele) {
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
        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();
        List<Programare> programariAnulabile = new ArrayList<>();

        for(Programare prog : toateProgramarile) {
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
            ProgramareService.getInstance().update(progDeAnulat);
            System.out.println("Programarea a fost anulata cu succes!");
        }
        else if(index!=0)
        {
            System.out.println("Optiune invalida");
        }
    }

    private void platesteFacturi(Pacient pacientLogat) {
        System.out.println("----Plata facturi----");

        List<Factura> toateFacturile = FacturaService.getInstance().readAll();
        List<Factura> facturiDePlatit = new ArrayList<>();

        for (Factura f : toateFacturile) {
            if (f.getProgramare().getPacient().getIdPacient() == pacientLogat.getIdPacient()
                    && f.getStatusPlata().equals(Factura.STATUS_PLATA_ASTEPTARE)) {
                facturiDePlatit.add(f);
            }
        }

        if (facturiDePlatit.isEmpty()) {
            System.out.println("Nu aveti nicio factura de platit.");
            return;
        }

        System.out.println("Facturile dvs neplatite: ");
        for (int i = 0; i < facturiDePlatit.size(); i++) {
            Factura f = facturiDePlatit.get(i);
            System.out.println((i + 1) + ". Factura #" + f.getIdFactura() +
                    " | Serviciu: " + f.getProgramare().getServiciu().getNumeServiciu() +
                    " | Suma: " + f.getPret() + " RON" +
                    " | Data emiterii: " + f.getDataEmiterii());
        }
        System.out.println("0. Anuleaza");

        System.out.print("Alegeti numarul facturii pe care doriti sa o platiti: ");
        int alegere = -1;
        if (scanner.hasNextInt()) {
            alegere = scanner.nextInt();
            scanner.nextLine();
        }
        else {
            scanner.next();
        }

        if (alegere > 0 && alegere <= facturiDePlatit.size()) {
            Factura facturaAleasa = facturiDePlatit.get(alegere - 1);
            System.out.println("Se proceseaza plata pentru suma de " + facturaAleasa.getPret() + " RON aferenta facturii...");

            facturaAleasa.setStatusPlata(Factura.STATUS_PLATA_FINALIZATA);
            FacturaService.getInstance().update(facturaAleasa);

            System.out.println("Plata finalizata cu succes");
        } else if (alegere != 0) {
            System.out.println("Optiune incorecta");
        }
    }

    private void autentificareMedic() {
        System.out.println("----Autentificare medic----");
        System.out.println("Introduceti CNP-ul dvs: ");
        String cnpIntrodus = scanner.nextLine();

        Medic medicGasit = null;

        List<Medic> totiMedicii=MedicService.getInstance().readAll();

        for(Medic m : totiMedicii) {
            if(m.getCnp().equals(cnpIntrodus)) {
                medicGasit = m;
                break;
            }
        }

        if(medicGasit != null) {
            System.out.println("Introduceti parola: ");
            String parolaIntrodusa = scanner.nextLine();

            if(medicGasit.getParola().equals(parolaIntrodusa)) {
                System.out.println("Autentificare cu succes!");
                meniuMedic(medicGasit);
            } else {
                System.out.println("Parola incorecta. Acces respins.");
            }
        } else {
            System.out.println("Acces respins. CNP-ul introdus nu apartine niciunui medic inregistrat.");
        }
    }

    public void meniuMedic(Medic medicLogat) {
        int choice=-1;
        do {
            System.out.println("1. Vezi programarile de azi");
            System.out.println("2. Vezi toate programarile");
            System.out.println("3. Finalizeaza o consultatie");
            System.out.println("4. Elibereaza o reteta");
            System.out.println("5. Emite factura");
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
                    veziToateProgramarile(medicLogat);
                    break;
                case 3:
                    finalizeazaConsultatie(medicLogat);
                    break;
                case 4:
                    elibereazaReteta(medicLogat);
                    break;
                case 5:
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

        List<Programare> toateProgramarile=ProgramareService.getInstance().readAll();
        boolean gasit=false;

        for (Programare p : toateProgramarile) {
           if(p.getMedic().getIdMedic() == medicLogat.getIdMedic() && p.getDataSiOra().toLocalDate().equals(azi)) {
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

    private void veziToateProgramarile(Medic medicLogat) {
        System.out.println("---- Toate programarile (Istoric complet) ----");

        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();
        boolean gasit = false;

        for (Programare p : toateProgramarile) {
            if (p.getMedic().getIdMedic() == medicLogat.getIdMedic()) {
                System.out.println("- Data: " + p.getDataSiOra().toLocalDate() +
                        " | Ora: " + p.getDataSiOra().toLocalTime() +
                        " | Pacient: " + p.getPacient().getNume() + " " + p.getPacient().getPrenume() +
                        " | Serviciu: " + p.getServiciu().getNumeServiciu() +
                        " | Status: [" + p.getStatus() + "]");
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Nu s-au gasit programari in istoric pentru dvs.");
        }
    }

    private void finalizeazaConsultatie(Medic medicLogat) {
        System.out.println("----Finalizare consultatie----");
        java.time.LocalDate azi = java.time.LocalDate.now();

        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();
        List<Programare> activeAzi = new ArrayList<>();

        for (Programare p : toateProgramarile) {
            if (p.getMedic().getIdMedic() == medicLogat.getIdMedic() && p.getDataSiOra().toLocalDate().equals(azi)) {
                if (p.getStatus().equals(Programare.STATUS_IN_ASTEPTARE) || p.getStatus().equals(Programare.STATUS_CONFIRMATA)) {
                    activeAzi.add(p);
                }
            }
        }

        if(activeAzi.isEmpty()) {
            System.out.println("Nu aveti consultatii active (in asteptare) astazi");
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
            ProgramareService.getInstance().update(selectata);

            System.out.println("Consultatia pentru " + selectata.getPacient().getNume() + " a fost marcata ca finalizata");
        } else if(optiune!=0){
            System.out.println("Optiune invalida");
        }
    }

    private void elibereazaReteta(Medic medicLogat) {
        System.out.println("---Eliberare reteta---");

        java.time.LocalDate azi = java.time.LocalDate.now();
        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();
        List<Programare> consultatiiIncheiate = new ArrayList<>();

        for (Programare p : toateProgramarile) {
            if (p.getMedic().getIdMedic() == medicLogat.getIdMedic() 
                && p.getDataSiOra().toLocalDate().equals(azi) 
                && p.getStatus().equals(Programare.STATUS_FINALIZATA)) {
                consultatiiIncheiate.add(p);
            }
        }
        if (consultatiiIncheiate.isEmpty()) {
            System.out.println("Nu aveti nicio consultatie finalizata astazi pentru care sa puteti elibera o reteta.");
            return;
        }

        System.out.println("Selectati pacientul pentru reteta:");
        for (int i = 0; i < consultatiiIncheiate.size(); i++) {
            Pacient pac = consultatiiIncheiate.get(i).getPacient();
            System.out.println((i + 1) + ". " + pac.getNume() + " " + pac.getPrenume());
        }
        System.out.println("0. Inapoi");

        System.out.print("Alegerea dumneavoastra: ");
        int optiune = -1;
        if (scanner.hasNextInt()) {
            optiune = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.next();
        }

        if (optiune <= 0 || optiune > consultatiiIncheiate.size()) {
            if (optiune != 0) {
                System.out.println("Optiune invalida");
            }
            return;
        }

        Pacient pacientAles = consultatiiIncheiate.get(optiune - 1).getPacient();
        Reteta retetaNoua = new Reteta(medicLogat, pacientAles, azi);

        List<Medicament> catalogMedicamente = MedicamentService.getInstance().readAll();
        if (catalogMedicamente.isEmpty()) {
            System.out.println("Nu exista medicamente in catalog. Nu se poate elibera reteta.");
            return;
        }

        System.out.println("\nCatalog medicamente disponibile:");
        for (int i = 0; i < catalogMedicamente.size(); i++) {
            Medicament m = catalogMedicamente.get(i);
            System.out.println((i + 1) + ". " + m.getNume() + " " + m.getDozaj() + "mg | Substanta: " + m.getSubstantaActiva());
        }

        System.out.println("\nIntroduceti numarul din lista al medicamentului pentru a-l adauga (0 pentru 'Gata'):");
        while (true) {
            System.out.print("Alegere medicament: ");
            int indexMed = -1;
            if (scanner.hasNextInt()) {
                indexMed = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.next();
            }

            if (indexMed == 0) {
                break;
            } else if (indexMed > 0 && indexMed <= catalogMedicamente.size()) {
                Medicament medicamentAles = catalogMedicamente.get(indexMed - 1);
                retetaNoua.addMedicament(medicamentAles);
                System.out.println("-> " + medicamentAles.getNume() + " a fost adaugat.");
            } else {
                System.out.println("Optiune invalida. Mai incercati sau apasati 0 pentru a termina.");
            }
        }

        if (!retetaNoua.getMedicamente().isEmpty()) {
            RetetaService.getInstance().create(retetaNoua);
            System.out.println("Reteta a fost salvata cu succes in baza de date pentru pacientul " + pacientAles.getNume());
        } else {
            System.out.println("⚠ Reteta nu a fost creata deoarece nu ati adaugat niciun medicament.");
        }
    }
    private void emiteFactura(Medic medicLogat) {
        System.out.println("----Emitere factura----");
        java.time.LocalDate azi = java.time.LocalDate.now();

        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();
        List<Factura> toateFacturile = FacturaService.getInstance().readAll();
        List<Programare> programariEligibile = new ArrayList<>();

        for (Programare p : toateProgramarile) {
            if (p.getMedic().getIdMedic() == medicLogat.getIdMedic()
                    && p.getDataSiOra().toLocalDate().equals(azi)
                    && p.getStatus().equals(Programare.STATUS_FINALIZATA)) {

                boolean areFactura = false;
                for (Factura f : toateFacturile) {
                    if (f.getProgramare().getIdProgramare() == p.getIdProgramare()) {
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
            System.out.println("Nu exista nicio consultatie finalizata astazi care sa necesite emiterea unei facturi.");
            return;
        }

        System.out.println("Selectati pacientul pentru care emiteti factura:");
        for (int i = 0; i < programariEligibile.size(); i++) {
            Programare p = programariEligibile.get(i);
            System.out.println((i + 1) + ". Pacient: " + p.getPacient().getNume() + " " + p.getPacient().getPrenume() +
                    " | Serviciu: " + p.getServiciu().getNumeServiciu());
        }

        System.out.print("Alegerea dumneavoastra: ");
        int optiune = -1;
        if (scanner.hasNextInt()) {
            optiune = scanner.nextInt();
            scanner.nextLine();
        }
        else {
            scanner.next();
        }

        if (optiune > 0 && optiune <= programariEligibile.size()) {
            Programare selectata = programariEligibile.get(optiune - 1);
            double pretTotal = selectata.getServiciu().getPret() + medicLogat.getCostConsultatie();

            Factura facturaNoua = new Factura(pretTotal, azi, selectata);
            FacturaService.getInstance().create(facturaNoua);

            System.out.println("Factura in valoare de " + pretTotal + " RON a fost emisa cu succes pentru pacientul " + selectata.getPacient().getNume());
        } else {
            System.out.println("Optiune invalida");
        }
    }
}
