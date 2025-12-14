package org.game.units.impl;

import org.game.model.EnemyType;
import org.game.units.Enemy;
import org.game.world.CellsMap;

public class Patrol extends Enemy {

    public Patrol() {
        setHp(100);
        setMaxHp(100);
        setDamage(10);
        setArmor(10);
    }

    @Override
    public String getIcon() {
        return EnemyType.PATROL.getIcon();
    }



    @Override
    public void attack() {

    }
}
