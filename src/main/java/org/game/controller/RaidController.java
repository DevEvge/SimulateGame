package org.game.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.game.config.RaidConfig;
import org.game.config.RaidConfig.RaidLocationProps;
import org.game.model.Direction;
import org.game.model.Locations;
import org.game.model.Point;
import org.game.model.RaidState;
import org.game.player.model.Hero;
import org.game.units.EnemyFactory;
import org.game.world.Cell;
import org.game.world.CellsMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Data
@Service
@RequiredArgsConstructor
public class RaidController {
    private final RaidConfig raidConfig;
    private final EnemyFactory factory;
    private Hero hero;
    private CellsMap map;
    private RaidState state = RaidState.EXPLORING;

    public void startRaid(Locations location) {
        String key = location.name().toLowerCase();
        RaidLocationProps props = raidConfig.getLocations().get(key);
        map = new CellsMap(props);
        state = RaidState.EXPLORING;

        map.populateEnemies(factory);
        spawnHero(props);
        raidStatistic(location, props);
    }


    public void heroMove(Direction dir) {
        hero.manualMove(map, dir);
        Cell currentCell = hero.getCurrentCell();
        if (currentCell.getAllResidents().size() > 1) {
            state = RaidState.COMBAT_CHOICE;
        }
    }

    public void fight() {
        System.out.println("Тут будет бой");
        state = RaidState.EXPLORING;
    }

    public void escape() {
        System.out.println("Тут будет сбег");
        state = RaidState.EXPLORING;
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
        map.getCell(spawnPoint.getX(), spawnPoint.getY())
                .ifPresent(cell -> cell.addResident(hero));
    }
}
