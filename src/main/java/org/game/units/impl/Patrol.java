package org.game.units.impl;

import lombok.Data;
import org.game.units.Entity;
import org.game.world.Cell;

@Data
public class Patrol implements Entity {
    private Cell currentCell;

    public Patrol(Cell currentCell) {
        this.currentCell = currentCell;
    }
    @Override
    public void move() {}

    @Override
    public void attack() {}
}
