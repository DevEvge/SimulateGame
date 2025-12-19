package org.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String id;
    private String name;
    private ItemType type;
    private SlotType slot;
    private int price;

    private int minDamage;
    private int maxDamage;
    private double critChance;

    private int defense;
    private int healAmount;
}
