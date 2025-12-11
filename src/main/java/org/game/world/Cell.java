package org.game.world;

import lombok.Data;
import lombok.ToString;
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
    private Map<Class<? extends Enemy>, List<Enemy>> residents;

    public Cell(int x, int y) {
        this.residents = new HashMap<>();
        this.x = x;
        this.y = y;
    }

    public void addResident(Enemy enemy) {
        residents.computeIfAbsent(enemy.getClass(), k -> new ArrayList<>()).add(enemy);
        enemy.setCurrentCell(this);
    }

    public void removeResident(Enemy enemy) {
        Class<? extends Enemy> type = enemy.getClass();

        if (residents.containsKey(type)) {
            List<Enemy> enemies = residents.get(type);
            enemies.remove(enemy);

            if (enemies.isEmpty()) {
                residents.remove(type);
            }
        }
        enemy.setCurrentCell(null);
    }

    public List<Enemy> getAllResidents() {
        List<Enemy> all = new ArrayList<>();
        for (List<Enemy> enemies : residents.values()) {
            all.addAll(enemies);
        }
        return all;
    }

    public boolean isEmpty(){
        return  residents.isEmpty();
    }
}
