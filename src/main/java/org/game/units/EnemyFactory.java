package org.game.units;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.game.config.EnemiesConfig;
import org.game.model.EnemyType;
import org.game.utils.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnemyFactory {
    private final EnemiesConfig enemiesConfig;

    public Enemy createEnemy(List<EnemyType> allowedTypes) {
        if (allowedTypes == null || allowedTypes.isEmpty()) return null;

        EnemyType type = RandomUtils.getRandomElement(allowedTypes);
        String key = type.name().toLowerCase();

        var props = enemiesConfig.getEnemies().get(key);
        if (props == null) {
            log.error("CRITICAL: Не знайдено конфіг для ворога: {}", key);
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
