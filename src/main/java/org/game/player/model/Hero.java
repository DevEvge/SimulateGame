package org.game.player.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.game.model.Direction;
import org.game.units.Creature;
import org.game.world.Cell;
import org.game.world.CellsMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Creature {
    private int xp = 0;
    private int lvl = 1;
    private int money = 0;
    private int materials = 0;
    private String icon = "\uD83D\uDD34";

    public Hero(String name) {
        setName(name);
        setHp(100);
        setMaxHp(100);
        setDamage(10);

    }

    public void manualMove(CellsMap map, Direction dir) {
        Cell currentCell = getCurrentCell();
        if (currentCell == null) return;

        int newX = currentCell.getX() + dir.getDx();
        int newY = currentCell.getY() + dir.getDy();

        if (!map.isValid(newX, newY)) {
            System.out.println("Некорректные координаты");
            return;
        }

        Cell targetCell = map.getCell(newX, newY);
        moveTo(targetCell);
    }
}
