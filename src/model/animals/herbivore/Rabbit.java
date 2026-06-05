package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Rabbit extends Herbivore {

    protected Rabbit(int x, int y) {
        super(AnimalKind.RABBIT, x, y);
    }

    public static Rabbit create(int x, int y) {
        return new Rabbit(x, y);
    }
}
