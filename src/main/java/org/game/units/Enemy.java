package org.game.units;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.game.model.EnemyType;
import org.game.utils.RandomUtils;
import org.game.world.Cell;
import org.game.world.CellsMap;

import java.util.Map;


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
        int dx = RandomUtils.getInt(-1, 1);
        int dy = RandomUtils.getInt(-1, 1);

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
