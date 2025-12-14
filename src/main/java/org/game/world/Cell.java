package org.game.world;

import lombok.Data;
import lombok.ToString;
import org.game.units.Creature;
import org.game.units.Enemy;
import org.game.units.EnemyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cell {
    private int x;
    private int y;
    @ToString.Exclude
    private Map<Class<? extends Creature>, List<Creature>> residents;

    public Cell(int x, int y) {
        this.residents = new HashMap<>();
        this.x = x;
        this.y = y;
    }

    public void addResident(Creature creature) {
        residents.computeIfAbsent(creature.getClass(), k -> new ArrayList<>()).add(creature);
        creature.setCurrentCell(this);
    }

    public void removeResident(Creature creature) {
        Class<? extends Creature> type = creature.getClass();

        if (residents.containsKey(type)) {
            List<Creature> enemies = residents.get(type);
            enemies.remove(creature);

            if (enemies.isEmpty()) {
                residents.remove(type);
            }
        }
        creature.setCurrentCell(null);
    }

    public List<Creature> getAllResidents() {
        List<Creature> all = new ArrayList<>();
        for (List<Creature> creatures : residents.values()) {
            all.addAll(creatures);
        }
        return all;
    }

    public boolean isEmpty(){
        return  residents.isEmpty();
    }
}
