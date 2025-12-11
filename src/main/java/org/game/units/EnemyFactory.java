package org.game.units;

import org.game.model.EnemyType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EnemyFactory {
    private final Random random = new Random();

    public Entity createEnemy() {
        return null;
    }
}
