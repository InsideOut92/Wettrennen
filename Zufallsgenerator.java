import java.util.Random;

public class Zufallsgenerator {
    public static double doubleGen(double min, double max) {
        Random rnd = new Random();
        return rnd.nextDouble() * (max - min) + min;
    }

    public static int intGen(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(max - min) + min;
    }

    public static boolean boolGen(){
        Random rnd = new Random();
        return rnd.nextBoolean();
    }

    public static String carPicker(){
        String[] car = {"Rennauto", "SUV"};

        return car[intGen(0, car.length)];
    }

    public static String colorGen(){
        String[] color = {"Blau", "Schwarz", "Grün", "Rot", "Lilablassblaukariert",
                "Pink-Camouflage", "Weiß", "Kamellippenblau"};
        // color[2]
        return color[intGen(0, color.length)];
    }

    public static String stringGen(int anzahlZeichen){
        String ergebnis = "";
        for(int i = 0; i < anzahlZeichen; i++){
            int genInt = intGen(65, 90);
            char step = (char) genInt;
            ergebnis += step;
        }
        return ergebnis;
    }

    public static void main(String[] args) {
        System.out.println(doubleGen(50, 250));
        System.out.println(doubleGen(10, 30));
        System.out.println(intGen(50, 250));
        System.out.println(intGen(10, 30));
        System.out.println(colorGen());
        System.out.println(stringGen(20));
        System.out.println(stringGen(10));
        for(int i = 0; i < 20; i++){
            System.out.println(i + " : " + carPicker());
        }
    }
}
