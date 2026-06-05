package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Sheep extends Herbivore {
    protected Sheep(int x, int y) {
        super(AnimalKind.SHEEP, x, y);
    }

    public static Sheep create(int x, int y) {
        return new Sheep(x, y);
    }
}
