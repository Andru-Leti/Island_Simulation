package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Mouse extends Herbivore {
    protected Mouse(int x, int y) {
        super(AnimalKind.MOUSE, x, y);
    }

    public static Mouse create(int x, int y) {
        return new Mouse(x, y);
    }

}
