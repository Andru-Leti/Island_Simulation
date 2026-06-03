package model.animals;

import model.AnimalKind;
import model.Herbivore;

public class Duck extends Herbivore {

    protected Duck(AnimalKind animalKind, int x, int y) {
        super(animalKind, x, y);
    }

    @Override
    public void eat() {
        // реализация логигки и для травоядных и для хищников
    }
}
