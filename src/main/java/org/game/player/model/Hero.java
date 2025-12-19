package org.game.player.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.game.model.Direction;
import org.game.model.Item;
import org.game.model.SlotType;
import org.game.player.Equipment;
import org.game.player.Wallet;
import org.game.units.Creature;
import org.game.utils.RandomUtils;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.concurrent.ThreadLocalRandom;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Hero extends Creature {
    private int currentExp = 0;
    private int lvl = 1;
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
        int baseDamage = RandomUtils.getInt(getMinDamage(), getMaxDamage());

        int weaponDamage = equipment.getItem(SlotType.MAIN_HAND)
                .map(item -> RandomUtils.getInt(item.getMinDamage(), item.getMaxDamage()))
                .orElse(0);

        double critChance = equipment.getItem(SlotType.MAIN_HAND)
                .map(Item::getCritChance)
                .orElse(0.0);

        int total = baseDamage + weaponDamage;

        if (RandomUtils.checkChance(critChance)) {
            log.info("\uD83D\uDCA5 КРИТИЧЕСКИЙ УДАР!");
            total = (int) (total * 1.5);
        }
        return total;
    }

    public int calculateTotalDefense () {
        int armorDef = equipment.getItem(SlotType.BODY)
                .map(Item::getDefense)
                .orElse(0);

        return getDefense() + armorDef;
    }

    public void manualMove(CellsMap map, Direction dir) {
        if (getCurrentCell() == null) return;

        int newX = getCurrentCell().getX() + dir.getDx();
        int newY = getCurrentCell().getY() + dir.getDy();

        map.getCell(newX, newY).ifPresentOrElse(
                this::moveTo,
                () -> log.info("Некорректные координаты: {} {}", newX, newY)
        );
    }
}