//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cell {
//
//    int x;
//    int y;
//
//    int grass = 5;
//
//    List<Animal> animals = new ArrayList<>();
//
//    public Cell(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int getX() {
//        return x;
//    }
//    public int getY() {
//        return y;
//    }
//    public int getGrass() {
//        return grass;
//    }
//
//    public void setGrass(int grass) {
//        this.grass = grass;
//    }
//
//    // подумать для многопоточки что возвращать
//    public List<Animal> getAnimals() {
//        return animals;
//    }
//
//    public synchronized boolean tryAdd(Animal animal){
//        if(isFullForKind(animal)){
//            return false;
//        }
//
//        animals.add(animal);
//        animal.setPosition(x,y);
//        return true;
//    }
//
//    private boolean isFullForKind(Animal animal){
//        int howMany = 0;
//        for(Animal animal1: animals){
//            if(animal1.getAnimalKind() == animal.getAnimalKind()){
//                howMany++;
//            }
//        }
//        return howMany >= animal.getAnimalKind().maxAnimals;
//    }
//}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {

    private final int x;
    private final int y;
    private int grass = 5;
    private final List<Animal> animals = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public synchronized int getGrass() { return grass; }

    public synchronized void setGrass(int grass) {
        this.grass = Math.max(0, grass);
    }

    public synchronized boolean consumeGrass(int amount) {
        if (grass >= amount) {
            grass -= amount;
            return true;
        }
        return false;
    }

    public synchronized List<Animal> getAnimalsCopy() {
        return new ArrayList<>(animals);
    }

    public synchronized boolean tryAdd(Animal animal) {
        if (isFullForKind(animal)) {
            return false;
        }
        animals.add(animal);
        animal.setPosition(x, y);
        return true;
    }

    public synchronized boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }

    public synchronized boolean containsAnimal(Animal animal) {
        return animals.contains(animal);
    }

    public synchronized int getAnimalCount() {
        return animals.size();
    }

    private boolean isFullForKind(Animal animal) {
        int howMany = 0;
        for (Animal a : animals) {
            if (a.getAnimalKind() == animal.getAnimalKind()) {
                howMany++;
            }
        }
        return howMany >= animal.getAnimalKind().maxAnimals;
    }
}
