import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Wettrennen {
    private ArrayList<Fahrzeug> teilnehmerListe = new ArrayList<>();
    private int rennDauer = 0;
    private int teilnehmerZahl = 0;

    private int zuschauerZahl = 0;

    private ArrayList<Zuschauer> zuschauerListe = new ArrayList<>();

    public Wettrennen(){}

    public void initializeRace(){
        System.out.println("Wie lange soll das Rennen dauern?");
        this.rennDauer = intEinlesen();

        System.out.println("Wie viele Teilnehmer soll das Rennen haben?");
        this.teilnehmerZahl = intEinlesen();

        for(int i = 0; i < teilnehmerZahl; i++){
            System.out.println("Bitte geben Sie das " + (i + 1)  + ".te Auto ein: ");
            Fahrzeug auto = autoErstellen();

            teilnehmerListe.add(auto);
        }

        startRace();

        /*
        Rennauto auto = new Rennauto();
        for(int i = 0; i < teilnehmerListe.size(); i++){
            Rennauto temp_auto = teilnehmerListe.get(i);
            if(i == 0){
                auto = temp_auto;
            } else {
                if(temp_auto.getStrecke() > auto.getStrecke()){
                    auto = temp_auto;
                }
            }
        }
        */

        sortWinner();

        siegerehrung();
    }

    public void raceInitializer(){
        System.out.println("Wie lange soll das Rennen dauern?");
        this.rennDauer = intEinlesen();

        System.out.println("Wie viele Teilnehmer soll das Rennen haben?");
        this.teilnehmerZahl = intEinlesen();

        createFahrzeuge();

        startRace();

        sortWinner();

        siegerehrung();
    }

    public void createFahrzeuge(){
        for(int i = 0; i < this.teilnehmerZahl; i++){
            Fahrzeug fahrzeug = new Fahrzeug();
            String car = Zufallsgenerator.carPicker();

            if(car.equals("Rennauto")){
                fahrzeug = new Rennauto(
                        Zufallsgenerator.doubleGen(30, 90),
                        Zufallsgenerator.doubleGen(30, 90),
                        Zufallsgenerator.doubleGen(2.0, 8.9),
                        Zufallsgenerator.doubleGen(140, 350),
                        Zufallsgenerator.doubleGen(140, 350),
                        Zufallsgenerator.stringGen(8),
                        Zufallsgenerator.colorGen(),
                        Zufallsgenerator.stringGen(15),
                        Zufallsgenerator.doubleGen(20, 140000),
                        0.0,
                        Zufallsgenerator.boolGen(),
                        Zufallsgenerator.doubleGen(50, 250),
                        Zufallsgenerator.doubleGen(14, 95),
                        Zufallsgenerator.intGen(200, 500));
            } else if (car.equals("SUV")) {
                fahrzeug = new SUV(
                        Zufallsgenerator.doubleGen(30, 90),
                        Zufallsgenerator.doubleGen(30, 90),
                        Zufallsgenerator.doubleGen(2.0, 8.9),
                        Zufallsgenerator.doubleGen(140, 350),
                        Zufallsgenerator.doubleGen(140, 350),
                        Zufallsgenerator.stringGen(8),
                        Zufallsgenerator.colorGen(),
                        Zufallsgenerator.stringGen(15),
                        Zufallsgenerator.doubleGen(20, 140000),
                        0.0,
                        Zufallsgenerator.boolGen(),
                        Zufallsgenerator.doubleGen(50, 250),
                        Zufallsgenerator.doubleGen(14, 95),
                        Zufallsgenerator.boolGen()
                );
            }


            teilnehmerListe.add(fahrzeug);
            /*
            double tankvolumen, double tankinhalt, double vebrauch, double maxSpeed,
            double currentSpeed, String kennzeichen, String color, String driver,
            double strecke, double rennstrecke, boolean isFueling, double money,
            double reifenProzent
            */

        }
    }

    public void siegerehrung(){
        System.out.println("Siegerehrung");
        int places = Math.min(this.teilnehmerListe.size(), 3);
        for(int i = 0; i < places; i++){
            Fahrzeug auto = this.teilnehmerListe.get(i);
            System.out.println((i + 1) + ".Platz: ");
            System.out.println(auto.toString());
        }
    }

    public void sortWinner(){
        // Hier ist noch ein Fehler enthalten!
        Fahrzeug temp;
        int size = this.teilnehmerListe.size();
        for(int i = 0; i < size; i++){
            for (int j = 1; j < (size -1); j++){
                if(this.teilnehmerListe.get(j - 1).getRennstrecke() > this.teilnehmerListe.get(j).getRennstrecke()){
                    temp = this.teilnehmerListe.get(j - 1);
                    this.teilnehmerListe.set(j -1, this.teilnehmerListe.get(j));
                    this.teilnehmerListe.set(j, temp);
                }
            }
        }
    }

    public void startRace(){
        System.out.println("-----Beginne Countdown-----");
        for(int i = 10; i >= 0; i--){
            if(i != 0){
                System.out.println("\t\t " + i);
            }
        }

        for(int j = 0; j < this.rennDauer; j++){
            System.out.println("Zeitvorgang: " + j);

            for(int i = 0; i < teilnehmerListe.size(); i++){
                Fahrzeug auto = teilnehmerListe.get(i);
                auto.drive(1);
                System.out.println(auto.kennzeichenStrecke());
            }
        }
    }

    public Fahrzeug autoErstellen(){
        boolean isValid = false;
        Fahrzeug auto;

        do{
            System.out.println("Bitte geben Sie das maximale Tankvolumen ein: ");
            double tankvolumen = doubleEinlesen();
            System.out.println("Bitte geben Sie den aktuellen Tankinhalt ein: ");
            double tankinhalt = doubleEinlesen();
            System.out.println("Bitte geben Sie den Verbrauch des Autos ein: ");
            double verbrauch = doubleEinlesen();
            System.out.println("Bitte geben Sie die Höchstgeschwindigkeit des Autos ein: ");
            double maxSpeed = doubleEinlesen();
            System.out.println("Bitte geben Sie die aktuelle Geschwindigkeit des Autos ein: ");
            double currentSpeed = doubleEinlesen();
            System.out.println("Bitte geben Sie das Kennzeichen des Autos ein: ");
            String kennzeichen = stringEinlesen();
            System.out.println("Bitte geben Sie die Farbe des Autos ein: ");
            String color = stringEinlesen();
            System.out.println("Bitte geben Sie den Fahrer des Autos ein: ");
            String driver = stringEinlesen();
            System.out.println("Bitte geben Sie den Kilometerstand des Autos ein: ");
            double strecke = doubleEinlesen();
            System.out.println("Bitte geben Sie das verfügbare Guthaben für das Fahrzeug ein: ");
            double money = doubleEinlesen();
            System.out.println("Bitte geben Sie den Zustand der Reifen in Prozent für das Fahrzeug ein: ");
            double reifenProzent = doubleEinlesen();

            auto = new Fahrzeug(tankvolumen, tankinhalt, verbrauch, maxSpeed, currentSpeed,
                    kennzeichen, color, driver, strecke, money, reifenProzent);

            System.out.println(auto);

            System.out.println("Möchten Sie dieses Auto erstellen? [Y/N]");
            String abfrage = stringEinlesen();
            if (abfrage.equals("Y") || abfrage.equals("y")){
                isValid = true;
            }

        } while (!isValid);

        return auto;
    }

    public int intEinlesen(){
        int zahl = 0;
        boolean isZahl = false;

        Scanner scanner = new Scanner(System.in);
        while(!isZahl){
            try{
                zahl = scanner.nextInt();
                isZahl = true;
            } catch (InputMismatchException e){
                System.out.println("Bitte geben Sie eine Zahl ein, die maximal 2 Milliarden ist.");
                scanner.next();
            }

            if(zahl < 0){
                isZahl = false;
                System.out.println("Bitte geben Sie eine positive Zahl ein.");
            }
        }

        return zahl;
    }

    public double doubleEinlesen(){
        double zahl = 0;
        boolean isZahl = false;

        Scanner scanner = new Scanner(System.in);
        while(!isZahl){
            try{
                zahl = scanner.nextDouble();
                isZahl = true;
            } catch (InputMismatchException e){
                System.out.println("Bitte geben Sie eine Zahl ein");
                scanner.next();
            }

            if(zahl < 0){
                isZahl = false;
                System.out.println("Bitte geben Sie eine positive Zahl ein.");
            }
        }

        return zahl;
    }

    public String stringEinlesen(){
        Scanner scanner = new Scanner(System.in);
        String entered = scanner.nextLine();
        return entered;
    }
}

