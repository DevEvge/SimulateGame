package org.game.world;

import lombok.Data;
import org.game.units.Enemy;

import java.util.List;

@Data
public class Cell {
    private int x;
    private int y;
    private List<Enemy> residents;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    //TODO: add methods add and delete residents
}
