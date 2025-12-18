package org.game.units;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.game.world.Cell;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Creature {
    private String name;
    @ToString.Exclude
    private Cell currentCell;
    private int hp;
    private int maxHp;
    private int agility;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private int level;


    public void moveTo(Cell targetCell) {
        if (currentCell != null) {
            currentCell.removeResident(this);
        }
        targetCell.addResident(this);
    }

    public abstract String getIcon();


}
