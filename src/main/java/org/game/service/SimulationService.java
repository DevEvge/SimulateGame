package org.game.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.game.controller.RaidController;
import org.game.model.Locations;
import org.game.units.Enemy;
import org.game.world.CellsMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationService {
    private final RaidController raidController;

    public void startGame(Locations locations) {
        CellsMap map = raidController.startRaid(locations);
        new Thread(() -> gameLoop(map)).start();

    }

    @SneakyThrows
    private void gameLoop(CellsMap map) {
        while (true) {
            System.out.println("\n".repeat(50));
            map.printMap();
            Thread.sleep(1000);
            updateState(map);
        }
    }

    private void updateState(CellsMap map) {
        List<Enemy> enemies = map.getAllEnemiesAsList();

        for (Enemy enemy: enemies) {
            enemy.move(map);
        }
    }

}
