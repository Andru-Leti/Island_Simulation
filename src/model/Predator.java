package model;

public abstract class Predator extends Animal {

    protected Predator(AnimalKind animalKind, int x, int y) {
        super(animalKind, x, y);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void move(Animal animal, Island island) {

    }

    @Override
    public void eat() {

    }
}
