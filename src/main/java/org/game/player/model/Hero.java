package org.game.player.model;

import lombok.ToString;
import org.game.model.Direction;
import org.game.units.Creature;
import org.game.world.Cell;
import org.game.world.CellsMap;

public class Hero extends Creature {

    private String name;

    //Stats
    private int xp = 0;
    private int lvl = 1;

    //Economy
    private int money = 0;
    private int materials = 0;


    public Hero(String name) {
        this.name = name;
    }

    public String getIcon() {
        return "\uD83D\uDD34";
    }

    @Override
    public void move(CellsMap cell) {
    }


    public void manualMove(CellsMap map, Direction dir) {
        Cell currentCell = getCurrentCell();
        if (currentCell == null) return;

        int newX = currentCell.getX() + dir.getDx();
        int newY = currentCell.getY() + dir.getDy();

        if (map.isValid(newX, newY)) {
            Cell targetCell = map.getCell(newX, newY);
            if (!targetCell.isEmpty()) {
                System.out.println("Клетка занята"); //TODO: Write when fight logic will done
                return;
            }
            currentCell.removeResident(this);
            targetCell.addResident(this);
        } else {
            System.out.println("Некорректные координатыw");
        }
    }
}
