package org.game.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.game.config.RaidConfig;
import org.game.config.RaidConfig.RaidLocationProps;
import org.game.model.*;
import org.game.player.model.Hero;
import org.game.service.ItemService;
import org.game.units.EnemyFactory;
import org.game.utils.RandomUtils;
import org.game.world.Cell;
import org.game.world.CellsMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class RaidController {
    private final RaidConfig raidConfig;
    private final EnemyFactory factory;
    private final ItemService itemService;
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
        Item medkit = itemService.getItem("medkit");
        hero.getInventory().addItem(medkit);

    }


    public void heroMove(Direction dir) {
        hero.manualMove(map, dir);
        Cell currentCell = hero.getCurrentCell();
        if (currentCell.getAllResidents().size() > 1) {
            state = RaidState.COMBAT_CHOICE;
        }
    }

    public void fight() {
        log.info("Player choose fight");
        state = RaidState.EXPLORING;
    }

    public void escape() {
        log.info("Player choose escape");
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
        Point spawnPoint = RandomUtils.getRandomElement(spawnPoints);
        hero = new Hero("Boris Britva");
        map.getCell(spawnPoint.getX(), spawnPoint.getY())
                .ifPresent(cell -> cell.addResident(hero));
    }
}
