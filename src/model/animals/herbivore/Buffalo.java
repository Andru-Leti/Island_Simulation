package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Buffalo extends Herbivore {

    protected Buffalo(int x, int y) {
        super(AnimalKind.BUFFALO, x, y);
    }

    public static Buffalo create(int x, int y) {
        return new Buffalo(x, y);
    }
}
