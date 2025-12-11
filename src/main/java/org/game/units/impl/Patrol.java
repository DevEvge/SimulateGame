package org.game.units.impl;

import org.game.model.EnemyType;
import org.game.units.Enemy;

public class Patrol extends Enemy {

    public Patrol() {
        setHealthPoints(100);
        setDamage(10);
        setArmor(10);
    }

    @Override
    public String getIcon() {
        return EnemyType.PATROL.getIcon();
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
