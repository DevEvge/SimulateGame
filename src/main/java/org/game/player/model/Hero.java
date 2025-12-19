package org.game.player.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.game.model.Direction;
import org.game.model.Item;
import org.game.model.SlotType;
import org.game.player.Equipment;
import org.game.player.Wallet;
import org.game.units.Creature;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Creature {
    private int currentExp = 0;
    private int lvl = 1;
    private int money = 0;
    private int materials = 0;
    private String icon = "\uD83D\uDD34";
    private Equipment equipment = new Equipment();
    private Wallet wallet = new Wallet();

    public Hero(String name) {
        setName(name);
        setHp(100);
        setMaxHp(100);
        setMinDamage(1);
        setMaxDamage(2);
        setAgility(5);
    }

    public int calculateTotalDamage() {
        int baseDamage = getRandom(getMinDamage(), getMaxDamage());

        int weaponDamage = 0;
        double critChance = 0.0;

        Item weapon = equipment.getItem(SlotType.MAIN_HAND);
        if (weapon != null) {
            weaponDamage = getRandom(getMinDamage(), getMaxDamage());
            critChance = weapon.getCritChance();
        }

        int totalDamage = baseDamage + weaponDamage;
        boolean isCrit = ThreadLocalRandom.current().nextDouble() < critChance;

        if (isCrit) {
            System.out.println("Crit damage!");
            totalDamage = (int) (totalDamage * 1.5);
        }
        return totalDamage;
    }

    public int calculateTotalDefense () {
        int totalDef = getDefense();
        Item armor = equipment.getItem(SlotType.BODY);

        if (armor != null) {
            totalDef += armor.getDefense();
        }

        return totalDef;
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

    private int getRandom(int min, int max) {
        if (min == max) return min;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}