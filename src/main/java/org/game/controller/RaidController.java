package org.game.controller;

import lombok.RequiredArgsConstructor;
import org.game.config.GameConfig;
import org.game.config.GameConfig.RaidLocationProps;
import org.game.units.EnemyFactory;
import org.game.world.CellsMap;
import org.game.model.Locations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaidController {
    private final GameConfig gameConfig;
    private final EnemyFactory factory;

    public CellsMap startRaid(Locations location){
        String key = location.name().toLowerCase();
        RaidLocationProps props = gameConfig.getLocations().get(key);

        if (props == null) {
            throw new IllegalArgumentException("Нет конфига для локации: " + key);
        }
        CellsMap map = new CellsMap(props);
        map.populateEnemies(factory);

        System.out.println("========================================");
        System.out.println("РЕЙД ЗАПУЩЕН: " + location);
        System.out.println("Размер карты: " + props.getWidth() + "x" + props.getHeight());
        System.out.println("Врагов заспавнилось: " + map.getAllEnemiesCount());
        System.out.println("Популяция: " + props.getPopulation());
        System.out.println("Разрешенные враги: " + props.getAllowedEnemies());
        System.out.println("Точки спавна: " + props.getSpawnPoints());
        System.out.println("Точки выхода: " + props.getExits());
        System.out.println("Кол-во клеток: " + (props.getWidth() * props.getHeight()));
        System.out.println("Кол-во пустых клеток: " + map.getEmptyCells());
        System.out.println("========================================");

        return map;
    }
}
