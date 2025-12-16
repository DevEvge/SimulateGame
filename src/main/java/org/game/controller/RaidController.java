package org.game.controller;

import lombok.RequiredArgsConstructor;
import org.game.config.GameConfig;
import org.game.config.GameConfig.RaidLocationProps;
import org.game.model.Direction;
import org.game.model.Locations;
import org.game.model.Point;
import org.game.player.model.Hero;
import org.game.units.EnemyFactory;
import org.game.world.CellsMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RaidController {
    private final GameConfig gameConfig;
    private final EnemyFactory factory;
    private Hero hero;
    private CellsMap map;

    public CellsMap startRaid(Locations location) {
        String key = location.name().toLowerCase();
        RaidLocationProps props = gameConfig.getLocations().get(key);
        map = new CellsMap(props);

        map.populateEnemies(factory);
        spawnHero(props);
        raidStatistic(location, props);

        return map;
    }


    public void heroMove(Direction dir) {
        hero.manualMove(map, dir);
    }

    private void raidStatistic(Locations location, RaidLocationProps props) {
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
    }

    private void spawnHero(RaidLocationProps props) {
        List<Point> spawnPoints = props.getSpawnPoints();
        Random random = new Random();
        Point spawnPoint = spawnPoints.get(random.nextInt(spawnPoints.size()));
        hero = new Hero("Boris Britva");
        map.getCell(spawnPoint.getX(), spawnPoint.getY()).addResident(hero);
    }


}
