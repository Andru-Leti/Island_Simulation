package service.eating;

import model.AnimalKind;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import static model.AnimalKind.*;

public class EatTable {
    private static final Map<AnimalKind, Map<AnimalKind, Integer>> EAT_PROBABILITY = new HashMap<>();

    static {
        for (AnimalKind kind : AnimalKind.values()) {
            EAT_PROBABILITY.put(kind, new EnumMap<>(AnimalKind.class));
        }

        put(WOLF, HORSE, 10);
        put(WOLF, DEER, 15);
        put(WOLF, RABBIT, 60);
        put(WOLF, MOUSE, 90);
        put(WOLF, GOAT, 60);
        put(WOLF, SHEEP, 70);
        put(WOLF, BOAR, 15);
        put(WOLF, BUFFALO, 10);
        put(WOLF, DUCK, 40);

        put(SNAKE, FOX, 15);
        put(SNAKE, RABBIT, 20);
        put(SNAKE, MOUSE, 40);
        put(SNAKE, DUCK, 10);

        put(FOX, RABBIT, 70);
        put(FOX, MOUSE, 90);
        put(FOX, DUCK, 60);
        put(FOX, CATERPILLAR, 40);

        put(BEAR, SNAKE, 80);
        put(BEAR, HORSE, 40);
        put(BEAR, DEER, 80);
        put(BEAR, RABBIT, 80);
        put(BEAR, MOUSE, 90);
        put(BEAR, GOAT, 70);
        put(BEAR, SHEEP, 70);
        put(BEAR, BOAR, 50);
        put(BEAR, BUFFALO, 20);
        put(BEAR, DUCK, 10);

        put(EAGLE, FOX, 10);
        put(EAGLE, RABBIT, 90);
        put(EAGLE, MOUSE, 70);
        put(EAGLE, DUCK, 80);

        put(MOUSE, CATERPILLAR, 90);

        put(BOAR, MOUSE, 50);
        put(BOAR, CATERPILLAR, 90);

        put(DUCK, CATERPILLAR, 90);
    }

    private static void put(AnimalKind predator, AnimalKind prey, int probability) {
        EAT_PROBABILITY.get(predator).put(prey, probability);
    }

    public static int get(AnimalKind predator, AnimalKind prey) {
        return EAT_PROBABILITY.getOrDefault(predator, Map.of()).getOrDefault(prey, 0);
    }

    public static int getAgainstPlant(AnimalKind animal) {
        switch (animal) {
            case HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR:
                return 100;
            default:
                return 0;
        }
    }

    /**
     * Проверяет, есть ли у хищника хотя бы одна жертва среди животных
     * @param predator вид хищника
     * @return true если может есть хотя бы одно животное
     */
    public static boolean hasAnyAnimalPrey(AnimalKind predator) {
        Map<AnimalKind, Integer> preyMap = EAT_PROBABILITY.get(predator);
        if (preyMap == null || preyMap.isEmpty()) {
            return false;
        }

        for (Map.Entry<AnimalKind, Integer> entry : preyMap.entrySet()) {
            if (entry.getValue() > 0) {
                return true;
            }
        }
        return false;
    }

}

