package model.animals.predator;

import model.AnimalKind;
import model.Predator;

public class Fox extends Predator {

    protected Fox(int x, int y) {
        super(AnimalKind.FOX, x, y);
    }

    public static Fox create(int x, int y) {
        return new Fox(x, y);
    }
}
