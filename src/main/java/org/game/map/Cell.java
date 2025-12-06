package org.game.map;

import lombok.Data;
import org.game.entity.Enemy;

import java.util.List;
import java.util.Map;

@Data
public class Cell {
    private Map<Direction, Cell> neighbors;
    private List<Enemy> residents;

}
