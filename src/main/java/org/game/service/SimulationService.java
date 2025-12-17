package org.game.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.game.controller.RaidController;
import org.game.model.Locations;
import org.game.model.RaidState;
import org.game.units.Enemy;
import org.game.world.CellsMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationService {
    private final RaidController raidController;

    @Scheduled(fixedRate = 1000)
    @SneakyThrows
    private void gameTick() {
        CellsMap map = raidController.getMap();
        if (map == null) return;
        if (raidController.getState() == RaidState.COMBAT_CHOICE) return;

        map.printMap();
        List<Enemy> enemies = map.getAllEnemies();
        for (Enemy enemy : enemies) {
            enemy.move(map);
        }
    }
}
