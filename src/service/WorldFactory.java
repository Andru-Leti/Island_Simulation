package service;

import model.Animal;
import model.AnimalKind;
import model.Cell;
import model.Island;
import model.animals.AnimalsFactory;

import java.util.concurrent.ThreadLocalRandom;

public class WorldFactory {

    public WorldFactory() {}

    public Island createWorld(int width, int height) {
        Cell[][] cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(x, y);

                for (AnimalKind kind : AnimalKind.values()) {
                    int count = ThreadLocalRandom.current().nextInt(kind.maxAnimals / 2, kind.maxAnimals + 1);
                    for (int i = 0; i < count; i++) {
                        Animal animal = AnimalsFactory.create(kind, x, y);
                        cell.tryAdd(animal);
                    }
                }

                cells[x][y] = cell;
            }
        }

        return new Island(width, height, cells);
    }
}