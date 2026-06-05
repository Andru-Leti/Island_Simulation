package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Horse extends Herbivore {

    protected Horse(int x, int y) {
        super(AnimalKind.HORSE, x, y);
    }

    public static Horse create(int x, int y) {
        return new Horse(x, y);
    }
}
