package org.game.controller;

import lombok.RequiredArgsConstructor;
import org.game.config.GameConfig;
import org.game.config.GameConfig.RaidLocationProps;
import org.game.map.Cell;
import org.game.map.CellsMap;
import org.game.map.Locations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaidController {
    private final GameConfig gameConfig;

    public CellsMap startRaid(Locations location){
        RaidLocationProps props = gameConfig.getLocations().get(location.name().toLowerCase());
        return new CellsMap(props);
    }

}
