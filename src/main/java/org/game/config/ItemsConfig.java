package org.game.config;

import lombok.Data;
import org.game.model.ItemType;
import org.game.model.SlotType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "items")
public class ItemsConfig {
    private Map<String, ItemsProps> items;

    @Data
    public static class ItemsProps {
        private String name;
        private ItemType type;
        private SlotType slot;
        private int price;
        private int minDamage;
        private int maxDamage;
        private double critChance;
        private int defense;
    }
}
