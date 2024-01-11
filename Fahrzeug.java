public class Fahrzeug {
    // Attribute
    private double tankvolumen = 60.0;
    private double tankinhalt = 50.0;
    private double vebrauch = 4.2;
    private double maxSpeed = 130.0;
    private double currentSpeed = 32.0;

    private String kennzeichen = "N-KD 1234";
    private String color = "Rot";
    private String driver = "The Kuschel Rock";

    private double strecke = 0.0;
    private double rennstrecke = 0.0;

    private boolean isFueling = false;

    private double reifenProzent = 95.0;
    private double money = 123.15;


    // Konstruktoren

    public Fahrzeug() { }

    public Fahrzeug(double tankvolumen, double tankinhalt, double vebrauch, double maxSpeed,
                    double currentSpeed, String kennzeichen, String color, String driver,
                    double strecke, double money, double reifenProzent) {
        this.tankvolumen = tankvolumen;
        setTankinhalt(tankinhalt);
        this.vebrauch = vebrauch;
        this.maxSpeed = maxSpeed;
        setCurrentSpeed(currentSpeed);
        this.kennzeichen = kennzeichen;
        this.color = color;
        this.driver = driver;
        this.strecke = strecke;
        this.money = money;
        this.reifenProzent = reifenProzent;

    }

    public Fahrzeug(double tankvolumen, double tankinhalt, double vebrauch, double maxSpeed,
                    double currentSpeed, String kennzeichen, String color, String driver,
                    double strecke, double rennstrecke, boolean isFueling, double money,
                    double reifenProzent) {
        this.tankvolumen = tankvolumen;
        setTankinhalt(tankinhalt);
        this.vebrauch = vebrauch;
        this.maxSpeed = maxSpeed;
        setCurrentSpeed(currentSpeed);
        this.kennzeichen = kennzeichen;
        this.color = color;
        this.driver = driver;
        this.strecke = strecke;
        this.rennstrecke = rennstrecke;
        this.isFueling = isFueling;
        this.money = money;
        this.reifenProzent = reifenProzent;
    }

    // Setter + Getter

    protected double getTankvolumen() {
        return tankvolumen;
    }

    private void setTankvolumen(double tankvolumen) {
        this.tankvolumen = tankvolumen;
    }

    public double getTankinhalt() {
        return tankinhalt;
    }

    public void setTankinhalt(double tankinhalt) {
        if (tankinhalt > this.tankvolumen){
            this.tankinhalt = this.tankvolumen;
        } else {
            this.tankinhalt = tankinhalt;
        }
    }

    public double getVebrauch() {
        return vebrauch;
    }

    public void setVebrauch(double vebrauch) {
        this.vebrauch = vebrauch;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    private void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        if(currentSpeed > this.maxSpeed){
            this.currentSpeed = this.maxSpeed;
        } else {
            this.currentSpeed = currentSpeed;
        }
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    private void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public double getStrecke() {
        return strecke;
    }

    protected void setStrecke(double strecke) {
        this.strecke = strecke;
    }

    public double getRennstrecke() {
        return rennstrecke;
    }

    protected void setRennstrecke(double rennstrecke) {
        this.rennstrecke = rennstrecke;
    }

    public boolean isFueling() {
        return isFueling;
    }

    public void setFueling(boolean fueling) {
        isFueling = fueling;
    }

    public double getReifenProzent() {
        return reifenProzent;
    }

    public void setReifenProzent(double reifenProzent) {
        this.reifenProzent = reifenProzent;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    // Methoden
    public void drive(int zeit) {
        if (this.tankinhalt > 0.0 && this.driver != null && zeit > 0.0 && !isFueling() && reifenProzent > 0.0) {
            // km = km/h * h / 60 min
            // km = km/min

            double strecke = this.currentSpeed * zeit / 60;

            // l = l/100km * km
            double verbrauch = this.getVebrauch() / 100 * strecke;

            this.strecke += strecke;
            this.rennstrecke += strecke;

            if (this.getVebrauch() > this.tankinhalt) {
                this.tankinhalt = 0.0;
            } else {
                this.tankinhalt -= verbrauch;
            }

            double reifenAbnutzung = 1.125;

            if(this.reifenProzent - reifenAbnutzung < 0.0){
                this.reifenProzent = 0.0;
            } else {
                this.reifenProzent -= reifenAbnutzung;
            }
        } else if (isFueling()) {
            tanken();
        } else if (reifenProzent <= 0.0) {
            this.reifenProzent = 100.0;
            System.out.println("Reifenwechsel");
        }
    }

    public String toString(){
        String autoinformation = "Das Auto mit dem Kennzeichen " + getKennzeichen() + " hat einen " +
                "Kilometerstand von " + round(this.strecke, 2) + " km, fährt derzeit " +
                getCurrentSpeed() + " km/h\n schnell und verbraucht dabei "+ getVebrauch() +
                " l/100km und hat noch " + round(getTankinhalt(), 2) + " l im Tank.\nDerzeitiger Fahrer ist: " +
                getDriver() + ", das Auto hat die Farbe: " + getColor() + " und im aktuellen Rennen: " +
                getRennstrecke() + " km zurück gelegt";
        return autoinformation;
    }

    public String kennzeichenStrecke(){
        return getKennzeichen() + " : " + getRennstrecke();
    }

    public double round(double value, int decimalPoint){
        // Potenzieren von 10 um decimalPoint => Anzahl der Nachkommastellen
        double d = Math.pow(10, decimalPoint);
        // Zahl, die gerundet werden soll, wird um entsprechend viele Nachkommastellen verschoben
        // und dann durch die Potenz geteilt nachdem die Zahl gerundet wurde.
        d = Math.round(value * d) / d;
        return d;
    }

    public void tanken(){
        double tankrate = this.tankvolumen / 10;
        this.setFueling(true);
        double pricePerLiter = 1.79;

        double pay = pricePerLiter * tankrate;


        if(this.money == 0.0){
            System.out.println("Ich habe kein Geld mehr");
        } else {
            if(pay > this.money) {
                tankrate = this.money / pricePerLiter;
                pay = tankrate * pricePerLiter;
            }

            // Prüfen ob aktueller Inhalt + Tankrate größer ist als Maximaler Inhalt
            if (this.tankinhalt + tankrate >= this.tankvolumen) {
                this.tankinhalt = this.tankvolumen;
                this.setFueling(false);
            } else {
                this.tankinhalt += tankrate;
            }
            System.out.println("Tankvorgang");
            this.money -= pay;
        }

    }

}
