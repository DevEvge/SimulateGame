package org.game.world;

import org.game.config.GameConfig.RaidLocationProps;
import org.game.units.Creature;
import org.game.units.Enemy;
import org.game.units.EnemyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CellsMap {

    private RaidLocationProps props;
    private Cell[][] cells;
    private final Random random;

    public CellsMap(RaidLocationProps props) {
        this.props = props;
        this.cells = new Cell[props.getWidth()][props.getHeight()];
        random = new Random();
        initGrid();
    }

    private void initGrid() {
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public void populateEnemies(EnemyFactory factory) {
        double chance = props.getPopulation();
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                if (random.nextDouble() < chance) {
                    Enemy enemy = factory.createEnemy(props.getAllowedEnemies());

                    if (enemy != null) {
                        cells[x][y].addResident(enemy);
                    }
                }
            }
        }
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < props.getWidth() && y >= 0 && y < props.getHeight();
    }

    public Cell getCell(int x, int y) {
        if (isValid(x, y)) {
            return cells[x][y];
        }
        return null;
    }

    public List<Creature> getAllEnemiesAsList() {
        List<Creature> allEnemies = new ArrayList<>();
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                allEnemies.addAll(cells[x][y].getAllResidents());
            }
        }
        return allEnemies;
    }

    public int getAllEnemiesCount() {
        int count = 0;
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                count += cells[x][y].getAllResidents().size();
            }
        }
        return count;
    }

    public int getEmptyCells() {
        int count = 0;
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                if (cells[x][y].isEmpty()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printMap() {
        System.out.println("--- КАРТА ---");

        for (int y = 0; y < props.getHeight(); y++) {
            for (int x = 0; x < props.getWidth(); x++) {
                Cell cell = cells[x][y];

                if (cell.isEmpty()) {
                    System.out.print(". "); // Пусто
                } else {
                    Creature creature = cell.getAllResidents().getFirst();
                    System.out.print(creature.getIcon() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

}
