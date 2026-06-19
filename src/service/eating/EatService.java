package service.eating;

import model.Animal;
import model.AnimalKind;
import model.Cell;
import model.animals.TickCounters;

import java.util.concurrent.ThreadLocalRandom;

public class EatService {

    /**
     * Основной метод: животное пытается поесть в указанной клетке.
     * Ограничение на количество попыток — чтобы избежать бесконечного поедания мелких жертв.
     */
    public void eat(Animal animal, Cell cell) {
        if (isFoodFull(animal)) {
            return;
        }

        AnimalKind predatorKind = animal.getAnimalKind();
        int eatAttempts = 0;
        int maxAttempts = 3;

        if (EatTable.hasAnyAnimalPrey(predatorKind)) {
            for (Animal prey : cell.getAnimalsCopy()) {
                if (prey == animal) continue;
                if (isFoodFull(animal)) break;
                if (eatAttempts >= maxAttempts) break;

                int chance = EatTable.get(predatorKind, prey.getAnimalKind());
                if (chance > 0 && ThreadLocalRandom.current().nextInt(100) < chance) {
                    eatPrey(animal, prey, cell);
                    eatAttempts++;
                }
            }
        }

        if (!isFoodFull(animal) && cell.getGrass() > 0 && EatTable.getAgainstPlant(predatorKind) > 0) {
            eatPlant(animal, cell);
        }
    }

    private boolean isFoodFull(Animal animal){
        return animal.getFoodKg() == animal.getAnimalKind().foodForFull;
    }

    /**
     * Поедание жертвы: хищник съедает часть или всю жертву,
     * после чего жертва удаляется из клетки.
     */
    private void eatPrey(Animal predator, Animal prey, Cell cell){
        if (!cell.containsAnimal(prey)) {
            return;
        }

        double needed = predator.getAnimalKind().foodForFull - predator.getFoodKg();
        double preyWeight = prey.getAnimalKind().weight;

        if(preyWeight < needed){
            predator.setFoodKg(predator.getFoodKg() + preyWeight);

        } else {
            predator.setFoodKg(predator.getAnimalKind().foodForFull);
        }
        cell.removeAnimal(prey);
        TickCounters.eatenAnimals++;
    }

    private void eatPlant(Animal animal, Cell cell) {
        double needed = animal.getAnimalKind().foodForFull - animal.getFoodKg();
        int grassToEat = (int) Math.min(needed, cell.getGrass());

        if (grassToEat > 0 && cell.consumeGrass(grassToEat)) {
            animal.setFoodKg(animal.getFoodKg() + grassToEat);
        }
    }
}
