package service;

import model.Animal;
import model.Island;
import model.Cell;
import model.animals.TickCounters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifeCycle {

    private final Island island;
    private final ExecutorService executorService;
    private final int fixedThreadPool;
    private final int grassGrowth;

    private final EatService eatService;
    private final MoveService moveService;
    private final ReproduceService reproduceService;

    public LifeCycle(Island island, int fixedThreadPool, int grassGrowth,
                     EatService eatService, MoveService moveService, ReproduceService reproduceService) {
        this.island = island;
        this.fixedThreadPool = fixedThreadPool;
        this.grassGrowth = grassGrowth;
        this.eatService = eatService;
        this.moveService = moveService;
        this.reproduceService = reproduceService;
        this.executorService = Executors.newFixedThreadPool(fixedThreadPool, r -> {
            Thread thread = new Thread(r, "island-thread");
            return thread;
        });
    }

    public void runTick() throws InterruptedException {
        TickCounters.reset();
        runReproduce();
        runEat();
        runMove();
        phaseStarve();
        growGrass();
    }

    private void runReproduce() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();
        for(int x = 0; x < island.getWidth(); x++){
            for(int y = 0; y < island.getHeight(); y++){
                int finalX = x;
                int finalY = y;
                tasks.add(() ->{
                        Cell cell = island.getCell(finalX, finalY);
                        reproduceService.reproduce(cell);
                        return null;
                });
            }
        }
        executorService.invokeAll(tasks);
    }

    private void runEat() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                tasks.add(() -> {
                    Cell cell = island.getCell(finalX, finalY);
                    for (Animal animal : cell.getAnimalsCopy()) {
                        eatService.eat(animal, cell);
                    }
                    return null;
                });
            }
        }

        executorService.invokeAll(tasks);
    }

    private void runMove() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                tasks.add(() -> {
                    Cell cell = island.getCell(finalX, finalY);
                    for (Animal animal : cell.getAnimalsCopy()) {
                        moveService.move(animal, cell);
                    }
                    return null;
                });
            }
        }

        executorService.invokeAll(tasks);
    }

    private void phaseStarve() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                tasks.add(() -> {
                    Cell cell = island.getCell(finalX, finalY);
                    applyHungerToCell(cell);
                    return null;
                });
            }
        }

        executorService.invokeAll(tasks);
    }

    private void applyHungerToCell(Cell cell) {
        double hungerRate = 0.1;

        for (Animal animal : cell.getAnimalsCopy()) {
            if (!cell.containsAnimal(animal)) {
                continue;
            }

            if (animal.getAnimalKind().foodForFull == 0) {
                continue;
            }

            double hungerLoss = animal.getAnimalKind().foodForFull * hungerRate;
            animal.setFoodKg(animal.getFoodKg() - hungerLoss);

            if (animal.getFoodKg() <= 0) {
                cell.removeAnimal(animal);
                TickCounters.deadAnimals++;
            }
        }
    }

    private void growGrass() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                tasks.add(() -> {
                    Cell cell = island.getCell(finalX, finalY);
                    int currentGrass = cell.getGrass();

                    int growth;
                    if (currentGrass < 50) {
                        growth = grassGrowth * 2;
                    } else if (currentGrass < 100) {
                        growth = grassGrowth;
                    } else if (currentGrass < 150) {
                        growth = grassGrowth / 2;
                    } else {
                        growth = 1;
                    }

                    int newGrass = Math.min(currentGrass + growth, 200);
                    cell.setGrass(newGrass);
                    return null;
                });
            }
        }

        executorService.invokeAll(tasks);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
