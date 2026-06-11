package model;

public enum AnimalKind {
    WOLF(50, 30, 3, 8, 4),
    SNAKE(15, 30, 1, 3, 6),
    FOX(8, 30, 2, 2, 5),
    BEAR(500, 5, 2, 80, 2),
    EAGLE(6, 20, 3, 1, 3),

    HORSE(400, 20, 4, 60, 1),
    DEER(300, 20, 4, 50, 1),
    RABBIT(2, 150, 2, 0.45, 10),
    MOUSE(0.05, 500, 1, 0.01, 12),
    GOAT(60, 140, 3, 10, 2),
    SHEEP(70, 140, 3, 15, 2),
    BOAR(400, 50, 2, 50, 5),
    BUFFALO(700, 10, 3, 100, 1),
    DUCK(1, 200, 4, 0.15, 8),
    CATERPILLAR(0.01, 1000, 0, 0, 20);

    public final double weight;
    public final int maxAnimals;
    public final int maxSpeed;
    public final double foodForFull;
    public final int maxOffspring;


    AnimalKind(double weight, int maxAnimals, int maxSpeed, double foodForFull, int maxOffspring) {
        this.weight = weight;
        this.maxAnimals = maxAnimals;
        this.maxSpeed = maxSpeed;
        this.foodForFull = foodForFull;
        this.maxOffspring = maxOffspring;
    }
}
