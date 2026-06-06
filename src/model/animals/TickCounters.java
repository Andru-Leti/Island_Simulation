package model.animals;

public class TickCounters {
    public static int eatenAnimals;
    public static int bornAnimals;
    public static int deadAnimals;

    public static void reset() {
        eatenAnimals = 0;
        bornAnimals = 0;
        deadAnimals = 0;
    }

    private TickCounters(){

    }
}
