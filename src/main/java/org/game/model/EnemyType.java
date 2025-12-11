package org.game.model;

import lombok.Getter;

@Getter
public enum EnemyType {
    PATROL("P"),
    RAT("r"),
    SNIPER("S");

    private final String icon;

    EnemyType(String icon) {
        this.icon = icon;
    }

}
