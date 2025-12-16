package org.game.units.impl;

import org.game.model.EnemyType;
import org.game.units.Enemy;
import org.game.world.CellsMap;

public class Rat extends Enemy {

    public Rat() {
        setName("Rat");
        setHp(100);
        setMaxHp(100);
        setDamage(5);
        setArmor(0);
    }

    @Override
    public String getIcon() {
        return EnemyType.RAT.getIcon();
    }


    @Override
    public void attack() {

    }
}
