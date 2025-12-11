package org.game.world;

import lombok.Data;
import org.game.units.Entity;

import java.util.List;

@Data
public class Cell {
    private int x;
    private int y;
    private List<Entity> residents;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    //TODO: add methods add and delete residents
}
