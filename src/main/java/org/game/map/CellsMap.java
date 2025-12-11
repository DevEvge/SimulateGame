package org.game.map;

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
        for (int y = 0; y < props.getWidth(); y++) {
            for (int x = 0; x < props.getWidth(); x++) {
                populateCell(x, y);
            }
        }
    }

    private void populateCell(int x, int y) {
        //TODO: end method
        cells[x][y] = new Cell(x, y);
    }
}
