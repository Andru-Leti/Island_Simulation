package service;

import model.Animal;
import model.AnimalKind;
import model.Cell;
import model.animals.AnimalsFactory;
import model.animals.TickCounters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ReproduceService {

    public ReproduceService() {}

    public void reproduce(Cell cell){
        Map<AnimalKind, List<Animal>> animalsByKind = cell.getAnimalsCopy().stream()
                .collect(Collectors.groupingBy(Animal::getAnimalKind));

        for(Map.Entry<AnimalKind, List<Animal>> entry: animalsByKind.entrySet()){
            AnimalKind kind = entry.getKey();
            List<Animal> animals = entry.getValue();

            int pairs = animals.size() / 2;
            for(int i = 0; i < pairs; i++){
                Animal parent1 = animals.get(i * 2);
                Animal parent2 = animals.get(i * 2 + 1);

                if(canReproduce(parent1, parent2, cell)){
                    reproducePair(parent1, parent2, cell, kind);
                }
            }
        }

    }

    private void reproducePair(Animal parent1, Animal parent2, Cell cell, AnimalKind kind) {
        int offspringCount = ThreadLocalRandom.current().nextInt(1, kind.maxOffspring + 1);

        double energyCost = kind.foodForFull * 0.3;

        parent1.setFoodKg(parent1.getFoodKg() - energyCost);
        parent2.setFoodKg(parent2.getFoodKg() - energyCost);
        int born = 0;
        for (int i = 0; i < offspringCount; i++) {
            Animal baby = AnimalsFactory.create(kind, cell.getX(), cell.getY());
            baby.setFoodKg(kind.foodForFull);

            if (cell.tryAdd(baby)) {
                born++;
            } else {
                break;
            }
        }
        TickCounters.bornAnimals += born;
    }

    private boolean canReproduce(Animal parent1, Animal parent2, Cell cell) {
        AnimalKind kind = parent1.getAnimalKind();

        double minFoodForReproduce = kind.foodForFull * 0.5;
        if (parent1.getFoodKg() < minFoodForReproduce || parent2.getFoodKg() < minFoodForReproduce){
            return false;
        }

        if(cell.isFullForKind(parent1)){
            return false;
        }

        return ThreadLocalRandom.current().nextInt(100) < 50;
    }
}
