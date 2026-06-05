package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Caterpillar extends Herbivore {

    protected Caterpillar(int x, int y) {
        super(AnimalKind.CATERPILLAR, x, y);
    }

    public static Caterpillar create(int x, int y) {
        return new Caterpillar(x, y);
    }
}
