package org.game;

import org.game.controller.RaidController;
import org.game.model.Locations;
import org.game.world.CellsMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        RaidController controller = context.getBean(RaidController.class);

        CellsMap map = controller.startRaid(Locations.SLUMS);
        map.printMap();

    }
}