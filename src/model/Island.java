package model;

public class Island {
    private final int width;
    private final int height;

    private final Cell[][] cells;

    public Island(int width, int height, Cell[][] cells) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public synchronized boolean relocate(Animal animal, int x, int y){
        // 1. Проверяем, находимся ли мы внутри острова( можно вынести для ускорения)
        // 2. Проверить может ли это животное туда зайти
        // 3. Если всё верно, то надо удалить животное со старой клетки
        return false;
    }
}
