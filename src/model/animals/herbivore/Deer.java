package model.animals.herbivore;

import model.AnimalKind;
import model.Herbivore;

public class Deer extends Herbivore {

    protected Deer(int x, int y) {
        super(AnimalKind.DEER, x, y);
    }

    public static Deer create(int x, int y) {
        return new Deer(x, y);
    }
}
