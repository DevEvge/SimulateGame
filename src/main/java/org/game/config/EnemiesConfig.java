package org.game.config;

import lombok.Data;
import org.game.model.EnemyType;
import org.game.model.Loot;
import org.game.model.Point;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "enemies")
public class EnemiesConfig {
    private Map<String, enemiesProp> locations;

    @Data
    public static class enemiesProp {
        private String name;
        private int hp;
        private int damageMin;
        private int damageMax;
        private int defense;
        private int agility;
        private int expReward;
        private int moneyMin;
        private int moneyMax;
        private Map<String, Loot> loot;

    }
}
