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
                    meniuMedic();
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
            System.out.println("6. Vezi fisa medicala");
            System.out.println("7. Plateste facturi");
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
                    System.out.println("[In lucru - metoda1");
                    break;
                case 2:
                    System.out.println("[In lucru - metoda2");
                    break;
                case 3:
                    System.out.println("----Creare programare noua----");
                    System.out.println("Alegeti medicul: ");
                    List<Medic> mediciDisponibili=service.getMedici();
                    for(int i=0;i<mediciDisponibili.size();i++)
                    {
                        System.out.println((i + 1) + ". " + mediciDisponibili.get(i).getNume() + " - " + mediciDisponibili.get(i).getPrenume() + " - " + mediciDisponibili.get(i).getSpecializare());
                    }
                    int indexMedic=scanner.nextInt()-1;
                    Medic medicAles=mediciDisponibili.get(indexMedic);

                    System.out.println("Alegeti serviciul medical:");
                    List<ServiciuMedical> serviciiDisponibile = service.getServicii();
                    for (int i=0;i<serviciiDisponibile.size();i++) {
                        System.out.println((i + 1) + ". " + serviciiDisponibile.get(i).getNumeServiciu() + " (" + serviciiDisponibile.get(i).getPret() + " RON)");
                    }
                    int indexServiciu = scanner.nextInt() - 1;
                    ServiciuMedical serviciuAles = serviciiDisponibile.get(indexServiciu);

                    System.out.println("Alegeti sala:");
                    List<Sala> saliDisponibile = service.getSali();
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
                    break;
                case 4:
                    System.out.println("[In lucru - metoda4");
                    break;
                case 5:
                    System.out.println("[In lucru - metoda5");
                    break;
                case 6:
                    System.out.println("[In lucru - metoda6");
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }

    public void meniuMedic() {
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
                    System.out.println("[In lucru - metoda1");
                    break;
                case 2:
                    System.out.println("[In lucru - metoda2");
                    break;
                case 3:
                    System.out.println("[In lucru - metoda3");
                    break;
                case 4:
                    System.out.println("[In lucru - metoda4");
                    break;
                case 0:
                    System.out.println("Revenire");
                    break;
                default:
                    System.out.println("Optiune invalida");
            }

        } while(choice!=0);
    }
}
