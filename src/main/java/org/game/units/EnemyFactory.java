package org.game.units;

import lombok.RequiredArgsConstructor;
import org.game.config.EnemiesConfig;
import org.game.model.EnemyType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EnemyFactory {
    private final Random random = new Random();
    private final EnemiesConfig enemiesConfig;

    public Enemy createEnemy(List<EnemyType> allowedTypes) {
        if (allowedTypes == null || allowedTypes.isEmpty()) {
            return null;
        }

        EnemyType type = allowedTypes.get(random.nextInt(allowedTypes.size()));
        String key = type.name().toLowerCase();

        var props = enemiesConfig.getEnemies().get(key);
        if (props == null) {
            System.out.println("No enemy config");
            return null;
        }

        return Enemy.builder()
                .type(type)
                .name(props.getName())
                .hp(props.getHp())
                .maxHp(props.getHp())
                .minDamage(props.getDamageMin())
                .maxDamage(props.getDamageMax())
                .defense(props.getDefense())
                .agility(props.getAgility())
                .expReward(props.getExpReward())
                .moneyMin(props.getMoneyMin())
                .moneyMax(props.getMoneyMax())
                .loot(props.getLoot())
                .build();

    }
}
