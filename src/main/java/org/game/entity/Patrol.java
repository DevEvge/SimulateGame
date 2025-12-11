package org.game.entity;

import lombok.Data;
import org.game.map.Cell;

@Data
public class Patrol implements Entity {
    private Cell currentCell;

    public Patrol(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
