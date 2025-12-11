package org.game.units;

import lombok.Data;
import lombok.ToString;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.Random;

@Data
public abstract class Enemy {

    private Random random = new Random();
    @ToString.Exclude
    private Cell currentCell;
    private int healthPoints;
    private int damage;
    private int armor;

    public void move(CellsMap map) {
        Cell current = getCurrentCell();
        if (currentCell == null) return;

        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;

        if (dx == 0 && dy == 0) return;

        int newX = current.getX() + dx;
        int newY = current.getY() + dy;

        if (map.isValid(newX, newY)) {
            Cell targetCell = map.getCell(newX, newY);
            current.removeResident(this);
            targetCell.addResident(this);
        }


    }

    public abstract void attack();
    public abstract String getIcon();

}
