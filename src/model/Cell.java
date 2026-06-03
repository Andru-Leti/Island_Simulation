package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    int x;
    int y;

    int grass = 5;

    List<Animal> animals = new ArrayList<>();

    public Cell(int x, int y, List<Animal> animals) {
        this.x = x;
        this.y = y;
        this.animals = animals;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGrass() {
        return grass;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public boolean tryAdd(Animal animal){
        if(isFullForKind(animal)){
            return false;
        }

        animals.add(animal);
        animal.setPosition(x,y);
        return true;
    }

    private boolean isFullForKind(Animal animal){
        int howMany = 0;
        for(Animal animal1: animals){
            if(animal1.getAnimalKind() == animal.getAnimalKind()){
                howMany++;
            }
        }
        return howMany >= animal.getAnimalKind().maxAnimals;
    }
}
