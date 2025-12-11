package org.game.config;

import lombok.Data;
import org.game.entity.EnemyType;
import org.game.map.Point;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "raid-configs")
public class GameConfig {
    private Map<String, RaidLocationProps> locations;

    @Data
    public static class RaidLocationProps {
        private int height;
        private int width;
        private double population;

        private List<EnemyType> allowedEnemies;

        private List<Point> spawnPoints;
        private List<Point> exits;
        private List<Point> pois;
    }
}
