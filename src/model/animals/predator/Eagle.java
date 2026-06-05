package model.animals.predator;

import model.AnimalKind;
import model.Predator;

public class Eagle extends Predator {

    protected Eagle(int x, int y) {
        super(AnimalKind.EAGLE, x, y);
    }

    public static Eagle create(int x, int y) {
        return new Eagle(x, y);
    }
}
