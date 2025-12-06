package org.game.map;

public class CellsMap {
    int width;
    int height;

    Cell[][] grid;


    public CellsMap() {
        this.grid = new Cell[width][height];
        fillGridAndPopulateCells();
    }


    private void fillGridAndPopulateCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Cell();

            }
        }
    }
}
