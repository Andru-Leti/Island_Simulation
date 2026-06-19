package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Duck extends Herbivore {

    protected Duck(int x, int y) {
        super(AnimalKind.DUCK, x, y);
    }

    public static Duck create(int x, int y) {
        return new Duck(x, y);
    }

}
