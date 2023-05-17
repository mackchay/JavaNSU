package ru.nsu.ccfit.haskov.model;

import ru.nsu.ccfit.haskov.model.Cell;

import java.util.Vector;

public class Move {
    private final Vector<Cell> painted;
    private final Cell addedTile;

    public Move(Vector<Cell> painted,
                Cell addedTile) {
        this.painted = painted;
        this.addedTile = addedTile;
    }

    public Cell getAddedTile() {
        return addedTile;
    }

    public Vector<Cell> getPainted() {
        return painted;
    }
}
