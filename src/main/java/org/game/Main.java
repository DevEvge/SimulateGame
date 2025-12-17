package org.game;

import org.game.controller.RaidController;
import org.game.model.Direction;
import org.game.model.Locations;
import org.game.model.RaidState;
import org.game.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        SimulationService simulationService = context.getBean(SimulationService.class);

        Scanner scanner = new Scanner(System.in);
        RaidController controller = simulationService.startGame(Locations.SLUMS);
        boolean isRunning = true;
        while (isRunning) {

            String input = scanner.nextLine();


            if (controller.getState() == RaidState.EXPLORING) {
                Direction dir = switch (input) {
                    case "w" -> Direction.UP;
                    case "s" -> Direction.DOWN;
                    case "a" -> Direction.LEFT;
                    case "d" -> Direction.RIGHT;
                    default -> null;
                };
                if (dir != null) {
                    controller.heroMove(dir);
                }
                if (input.equals("p")) {
                    isRunning = false;
                }
            } else if (controller.getState() == RaidState.COMBAT_CHOICE) {
                System.out.println("1. Атаковать\n2. Сбежать");
                if (input.equals("1")) controller.fight();
                if (input.equals("2")) controller.escape();


                if (input.equals("p")) {
                    isRunning = false;
                }

            }
        }
    }
}