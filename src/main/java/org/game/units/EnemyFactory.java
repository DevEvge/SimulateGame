package org.game.units;

import org.game.model.EnemyType;
import org.game.units.impl.Patrol;
import org.game.units.impl.Rat;
import org.game.units.impl.Sniper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EnemyFactory {
    private final Random random = new Random();

    public Enemy createEnemy(List<EnemyType> allowedTypes) {
        if (allowedTypes == null || allowedTypes.isEmpty()) {
            return null;
        }

        EnemyType type = allowedTypes.get(random.nextInt(allowedTypes.size()));
        return switch (type) {
            case PATROL -> new Patrol();
            case RAT -> new Rat();
            case SNIPER -> new Sniper();
            default -> throw new IllegalArgumentException("Unknown enemy type: " + type);
        };
    }
}
