package org.game.player;


import lombok.Data;

@Data
public class Wallet {
    private int money;

    public void add(int amaunt) {
        if (amaunt > 0) this.money += amaunt;
    }

    public boolean spend(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
}
