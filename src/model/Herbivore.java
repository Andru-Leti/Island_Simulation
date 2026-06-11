package model;

import service.EatService;
import service.MoveService;

public abstract class Herbivore extends Animal {

    protected Herbivore(AnimalKind animalKind, int x, int y) {
        super(animalKind, x, y);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void move(Animal animal, Cell currentCell) {

    }

    @Override
    public void eat() {

    }
}
