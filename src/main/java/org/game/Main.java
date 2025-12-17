package org.game;

import lombok.RequiredArgsConstructor;
import org.game.controller.RaidController;
import org.game.model.Direction;
import org.game.model.Locations;
import org.game.model.RaidState;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class Main implements CommandLineRunner {
    private final RaidController controller;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        controller.startRaid(Locations.SLUMS);

        boolean isRunning = true;
        while (isRunning) {
            // Рисуем меню, если бой
            if (controller.getState() == RaidState.COMBAT_CHOICE) {
                System.out.println("⚠️ ВРАГ ОБНАРУЖЕН! ⚠️");
                System.out.println("1. Атаковать");
                System.out.println("2. Сбежать");
            }

            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine();

            if (controller.getState() == RaidState.EXPLORING) {
                Direction dir = switch (input) {
                    case "w" -> Direction.UP;
                    case "s" -> Direction.DOWN;
                    case "a" -> Direction.LEFT;
                    case "d" -> Direction.RIGHT;
                    default -> null;
                };
                if (dir != null) controller.heroMove(dir);

            } else if (controller.getState() == RaidState.COMBAT_CHOICE) {
                if (input.equals("1")) controller.fight();
                if (input.equals("2")) controller.escape();
            }

            if (input.equals("p")) {
                isRunning = false;
                System.out.println("Игра завершена.");
                System.exit(0);
            }
        }
    }
}