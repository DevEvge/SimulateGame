package org.game.units;

import lombok.Data;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.Random;

@Data
public abstract class Enemy extends Creature {

    private Random random = new Random();
    private int armor;

    public void move(CellsMap map) {
        Cell current = getCurrentCell();
        if (current == null) return;

        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;

        if (dx == 0 && dy == 0) return;

        int newX = current.getX() + dx;
        int newY = current.getY() + dy;

        if (map.isValid(newX, newY)) {
            Cell targetCell = map.getCell(newX, newY);
            moveTo(targetCell);
        }
    }

    public abstract void attack();

    public abstract String getIcon();

}
