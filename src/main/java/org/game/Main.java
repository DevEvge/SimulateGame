package org.game;

import org.game.controller.RaidController;
import org.game.model.Locations;
import org.game.service.SimulationService;
import org.game.world.CellsMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        SimulationService simulationService = context.getBean(SimulationService.class);

        simulationService.startGame(Locations.SLUMS);
    }
}