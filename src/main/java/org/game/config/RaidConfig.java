package org.game.config;

import lombok.Data;
import org.game.model.EnemyType;
import org.game.model.Point;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "locations")
public class RaidConfig {
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
