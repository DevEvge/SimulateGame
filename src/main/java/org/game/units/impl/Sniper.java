package org.game.units.impl;

import org.game.model.EnemyType;
import org.game.units.Enemy;

public class Sniper extends Enemy {

    public Sniper() {
        setHealthPoints(100);
        setDamage(10);
        setArmor(10);
    }

    @Override
    public String getIcon() {
        return EnemyType.SNIPER.getIcon();
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {

    }
}
