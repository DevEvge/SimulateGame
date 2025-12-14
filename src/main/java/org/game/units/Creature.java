package org.game.units;

import lombok.Data;
import lombok.ToString;
import org.game.world.Cell;
import org.game.world.CellsMap;

@Data
public abstract class Creature {
    @ToString.Exclude
    private Cell currentCell;
    private int hp;
    private int maxHp;
    private int damage;


    public abstract String getIcon();
    public abstract void move(CellsMap cell);


}
