package model.animals;

import model.Animal;
import model.AnimalKind;
import model.animals.herbivore.*;
import model.animals.predator.*;

public class AnimalsFactory {

    private AnimalsFactory() {
    }

    public static Animal create(AnimalKind animalKind, int x, int y) {
        return switch (animalKind) {
            case BEAR -> Bear.create(x, y);
            case EAGLE -> Eagle.create(x, y);
            case FOX -> Fox.create(x, y);
            case SNAKE -> Snake.create(x, y);
            case WOLF -> Wolf.create(x, y);
            case BOAR -> Boar.create(x, y);
            case BUFFALO -> Buffalo.create(x, y);
            case CATERPILLAR -> Caterpillar.create(x, y);
            case DEER -> Deer.create(x, y);
            case DUCK -> Duck.create(x, y);
            case GOAT -> Goat.create(x, y);
            case HORSE -> Horse.create(x, y);
            case MOUSE -> Mouse.create(x, y);
            case RABBIT -> Rabbit.create(x, y);
            case SHEEP -> Sheep.create(x, y);
        };
    }
}
