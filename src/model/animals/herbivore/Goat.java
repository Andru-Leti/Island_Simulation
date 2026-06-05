package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Goat extends Herbivore {

    protected Goat(int x, int y) {
        super(AnimalKind.GOAT, x, y);
    }

    public static Goat create(int x, int y) {
        return new Goat(x, y);
    }
}
