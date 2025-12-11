package org.game.units.impl;

import org.game.model.EnemyType;
import org.game.units.Enemy;

public class Rat extends Enemy {

    public Rat() {
        setHealthPoints(100);
        setDamage(5);
        setArmor(0);
    }

    @Override
    public String getIcon() {
        return EnemyType.RAT.getIcon();
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
