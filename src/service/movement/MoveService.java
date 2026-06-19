package service.movement;

import model.Animal;
import model.Cell;
import model.Direction;
import model.Island;

import java.util.concurrent.ThreadLocalRandom;

public class MoveService {

    private final Island island;

    public MoveService(Island island) {
        this.island = island;
    }

    public void move(Animal animal, Cell currentCell) {
        int max_step = animal.getAnimalKind().maxSpeed;
        if (max_step == 0){
            return;
        }

        int steps = ThreadLocalRandom.current().nextInt(0, max_step + 1);

        int currentX = currentCell.getX();
        int currentY = currentCell.getY();

        for(int step = 0; step < steps; step++){
            Direction direction = Direction.random();

            int newX = currentX + direction.getDx();
            int newY = currentY + direction.getDy();
            if(isValidPosition(newX, newY)){
                Cell targetCell = island.getCell(newX, newY);

                if(relocate(animal, currentCell, targetCell)){
                    currentX = newX;
                    currentY = newY;
                    currentCell = targetCell;
                }
            }

        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < island.getWidth() && y >= 0 && y < island.getHeight();
    }

    /**
     * Безопасно перемещает животное из одной клетки в другую.
     * Для предотвращения deadlock'а блокировка клеток происходит в строгом порядке,
     * определяемом по hashCode().
     */
    private boolean relocate(Animal animal, Cell from, Cell to) {
        Cell first = from.hashCode() < to.hashCode() ? from : to;
        Cell second = from.hashCode() < to.hashCode() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (to.tryAdd(animal)) {
                    from.removeAnimal(animal);
                    return true;
                }
            }
        }
        return false;
    }
}
