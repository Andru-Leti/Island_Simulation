package model.animals.predator;

import model.AnimalKind;
import model.Predator;

public class Wolf extends Predator {

    protected Wolf( int x, int y) {
        super(AnimalKind.WOLF, x, y);
    }

    public static Wolf create(int x, int y) {
        return new Wolf(x, y);
    }
}
