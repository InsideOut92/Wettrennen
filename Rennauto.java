public class Rennauto extends Fahrzeug{
    // Attribute
    private int ps = 250;

    // Konstruktoren
    public Rennauto() {
    }

    public Rennauto(double tankvolumen, double tankinhalt, double vebrauch, double maxSpeed, double currentSpeed,
                    String kennzeichen, String color, String driver, double strecke, double money,
                    double reifenProzent, int ps) {
        super(tankvolumen, tankinhalt, vebrauch, maxSpeed, currentSpeed, kennzeichen, color, driver, strecke,
                money, reifenProzent);
        this.ps = ps;
    }

    public Rennauto(double tankvolumen, double tankinhalt, double vebrauch, double maxSpeed, double currentSpeed,
                    String kennzeichen, String color, String driver, double strecke, double rennstrecke,
                    boolean isFueling, double money, double reifenProzent, int ps) {
        super(tankvolumen, tankinhalt, vebrauch, maxSpeed, currentSpeed, kennzeichen, color, driver, strecke,
                rennstrecke, isFueling, money, reifenProzent);
        this.ps = ps;
    }

    // Methoden
    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void beschleunigen(){
        if(this.getCurrentSpeed() < this.getMaxSpeed()){
            this.setCurrentSpeed((this.getMaxSpeed()-this.getCurrentSpeed()) * 0.1);
            this.setVebrauch(this.getVebrauch()*1.1);
        } else {
            this.setVebrauch(this.getVebrauch() * 0.5);
        }
    }

    public void drive(int zeit) {
        if (this.getTankinhalt() > 0.0 && this.getDriver() != null &&
                zeit > 0.0 && !isFueling() && this.getReifenProzent() > 0.0) {
            // km = km/h * h / 60 min
            // km = km/min
            this.beschleunigen();

            double strecke = this.getCurrentSpeed() * zeit / 60;

            // l = l/100km * km
            double verbrauch = this.getVebrauch() / 100 * strecke;

            this.setStrecke(this.getStrecke() + strecke);
            this.setRennstrecke(this.getRennstrecke() + strecke);

            if (this.getVebrauch() > this.getTankinhalt()) {
                this.setTankinhalt(0.0);
            } else {
                this.setTankinhalt(this.getTankinhalt() - verbrauch);
            }

            double reifenAbnutzung = 1.125;

            if(this.getReifenProzent() - reifenAbnutzung < 0.0){
                this.setReifenProzent(0.0);
            } else {
                this.setReifenProzent(this.getReifenProzent() - reifenAbnutzung);
            }
        } else if (isFueling()) {
            tanken();
        } else if (this.getReifenProzent() <= 0.0) {
            this.setReifenProzent(100.0);
            System.out.println("Reifenwechsel");
        }
    }

    public String toString(){
        String autoinformation = "Das Auto mit dem Kennzeichen " + getKennzeichen() + " hat einen " +
                "Kilometerstand von " + round(this.getStrecke(), 2) + " km, fährt derzeit " +
                getCurrentSpeed() + " km/h\n schnell und verbraucht dabei "+ getVebrauch() +
                " l/100km und hat noch " + round(getTankinhalt(), 2) + " l im Tank.\nDerzeitiger Fahrer ist: " +
                getDriver() + ", das Auto hat die Farbe: " + getColor() + " und im aktuellen Rennen: " +
                getRennstrecke() + " km zurück gelegt \n" +
                "Ich bin ein Rennauto.";
        return autoinformation;
    }
}
