package org.game.world;

import org.game.config.RaidConfig.RaidLocationProps;
import org.game.units.Creature;
import org.game.units.Enemy;
import org.game.units.EnemyFactory;
import org.game.utils.RandomUtils;

import java.util.*;

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
        Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> RandomUtils.checkChance(props.getPopulation()))
                .forEach(cell -> {
                    Enemy enemy = factory.createEnemy(props.getAllowedEnemies());
                    if (enemy != null) cell.addResident(enemy);
                });
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < props.getWidth() && y >= 0 && y < props.getHeight();
    }

    public Optional<Cell> getCell(int x, int y) {
        if (isValid(x, y)) {
            return Optional.of(cells[x][y]);
        }
        return Optional.empty();
    }

    public List<Enemy> getAllEnemies() {
        List<Enemy> allEnemies = new ArrayList<>();
        for (int x = 0; x < props.getWidth(); x++) {
            for (int y = 0; y < props.getHeight(); y++) {
                for (Creature creature : cells[x][y].getAllResidents())
                    if (creature instanceof Enemy enemy) {
                        allEnemies.add(enemy);
                    }
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
        System.out.println("\n".repeat(50));
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
