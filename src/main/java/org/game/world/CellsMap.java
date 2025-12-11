package org.game.world;

import org.game.config.GameConfig.RaidLocationProps;

public class CellsMap {

    private RaidLocationProps props;
    private Cell[][] cells;

    public CellsMap(RaidLocationProps props) {
        this.props = props;
        this.cells = new Cell[props.getWidth()][props.getHeight()];
        initGrid();
    }

    private void initGrid() {
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                populateCell(x, y);
            }
        }
    }

    private void populateCell(int x, int y) {
        //TODO: end method
        cells[x][y] = new Cell(x, y);
    }
}
