package model;

import service.EatService;
import service.MoveService;

public abstract class Herbivore extends Animal {

    EatService eatService;
    MoveService moveService = new MoveService();

    protected Herbivore(AnimalKind animalKind, int x, int y) {
        super(animalKind, x, y);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void move(Animal animal, Island island) {
        moveService.move(animal, island);
    }

    @Override
    public void eat() {

    }
}
