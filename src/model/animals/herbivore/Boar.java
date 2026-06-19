package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Boar extends Herbivore {

    protected Boar(int x, int y) {
        super(AnimalKind.BOAR, x, y);
    }

    public static Boar create(int x, int y) {
        return new Boar(x, y);
    }

}
