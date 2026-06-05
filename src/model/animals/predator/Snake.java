package model.animals.predator;

import model.AnimalKind;
import model.Predator;

public class Snake extends Predator {
    protected Snake( int x, int y) {
        super(AnimalKind.SNAKE, x, y);
    }
    public static Snake create(int x, int y) {
        return new Snake(x, y);
    }
}
