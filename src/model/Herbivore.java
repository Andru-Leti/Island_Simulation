package model;

import service.EatService;

public abstract class Herbivore extends Animal {

    EatService eatService;

    protected Herbivore(AnimalKind animalKind, int x, int y) {
        super(animalKind, x, y);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void move() {

    }

    @Override
    public void eat() {

    }
}
