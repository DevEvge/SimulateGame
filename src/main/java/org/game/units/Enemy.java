package org.game.units;

import lombok.Data;
import lombok.ToString;
import org.game.world.Cell;

@Data
public abstract class Enemy {

    @ToString.Exclude
    private Cell currentCell;
    private int healthPoints;
    private int damage;
    private int armor;

    public abstract void move();
    public abstract void attack();

}
