package service;

import model.Animal;
import model.AnimalKind;
import model.Cell;
import model.Island;
import model.animals.TickCounters;

import java.util.HashMap;
import java.util.Map;

public class PrintStatistic {

    public static void print(Island island) {
        System.out.println("📊 ДИНАМИКА ПОПУЛЯЦИИ:");
        System.out.println("    🍼 Родилось:   " + TickCounters.bornAnimals);
        System.out.println("    🍽️  Съедено:    " + TickCounters.eatenAnimals);
        System.out.println("    💀 Умерло:     " + TickCounters.deadAnimals);

        System.out.println("📋 ЖИВОТНЫХ ПО ВИДАМ:");
        Map<AnimalKind, Integer> stats = getAnimalsStats(island);
        if (stats.isEmpty()) {
            System.out.println("    ⚠️ Нет животных на острове");
        } else {
            for (Map.Entry<AnimalKind, Integer> entry : stats.entrySet()) {
                System.out.printf("    %-12s: %d%n", entry.getKey().name(), entry.getValue());
            }
        }

        System.out.println("🌿 РАСТЕНИЯ:");
        System.out.println("    Всего травы: " + getTotalGrass(island) + " кг");
        System.out.println();
    }

    private static Map<AnimalKind, Integer> getAnimalsStats(Island island) {
        Map<AnimalKind, Integer> stats = new HashMap<>();
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                for (Animal animal : cell.getAnimalsCopy()) {
                    AnimalKind kind = animal.getAnimalKind();
                    stats.put(kind, stats.getOrDefault(kind, 0) + 1);
                }
            }
        }
        return stats;
    }

    private static int getTotalGrass(Island island) {
        int total = 0;
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                total += island.getCell(x, y).getGrass();
            }
        }
        return total;
    }
}