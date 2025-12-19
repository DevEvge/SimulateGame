package org.game.units;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.game.model.EnemyType;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Enemy extends Creature {

    private EnemyType type;
    private int expReward;
    private int moneyMin;
    private int moneyMax;
    private Map<String, Double> loot;



    public void move(CellsMap map) {
        Cell current = getCurrentCell();
        if (current == null) return;
        int dx = ThreadLocalRandom.current().nextInt(3) - 1;
        int dy = ThreadLocalRandom.current().nextInt(3) - 1;

        if (dx == 0 && dy == 0) return;
        int newX = current.getX() + dx;
        int newY = current.getY() + dy;

        if (map.isValid(newX, newY)) {
            map.getCell(newX, newY).ifPresent(this::moveTo);
        }
    }

    public void attack() {
    }

    public String getIcon() {
        return type.getIcon();
    }

}
