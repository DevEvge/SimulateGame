package org.game.map;

import lombok.Data;


public class CellsMap {
    private final int width;
    private final int height;
    private Cell[][] grid;

    public CellsMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        initGrid();
    }

    private void initGrid() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }
}
