package model;

public abstract class Animal {

    private final AnimalKind animalKind;

    private int x;
    private int y;

    private double foodKg;

    protected Animal(AnimalKind animalKind, int x, int y) {
        this.animalKind = animalKind;
        this.x = x;
        this.y = y;
        this.foodKg = animalKind.foodForFull;
    }

    public AnimalKind getAnimalKind() {
        return animalKind;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getFoodKg() {
        return foodKg;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void reproduce();
    public abstract void move();
    public abstract void eat();

    // своя реализация
    public void hungerTick(){

    }

    public boolean isDead(){
        return foodKg <= 0;
    }
}
