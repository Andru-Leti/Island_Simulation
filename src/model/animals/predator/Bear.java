package model.animals.predator;

import model.AnimalKind;
import model.Predator;

public class Bear extends Predator {

    protected Bear(int x, int y) {
        super(AnimalKind.BEAR, x, y);
    }

    public static Bear create(int x, int y) {
        return new Bear(x, y);
    }
}
