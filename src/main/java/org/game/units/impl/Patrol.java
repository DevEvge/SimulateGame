package org.game.units.impl;

import lombok.Data;
import lombok.ToString;
import org.game.units.Entity;
import org.game.world.Cell;

@Data
public class Patrol implements Entity {
    @ToString.Exclude
    private Cell currentCell;

    @Override
    public void move() {}

    @Override
    public void attack() {}
}
