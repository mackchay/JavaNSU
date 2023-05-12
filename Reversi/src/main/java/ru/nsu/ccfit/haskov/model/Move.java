package ru.nsu.ccfit.haskov.model;

import javafx.util.Pair;

import java.util.Vector;

public class Move {
    private final Vector<Pair<Integer, Integer>> painted;
    private final Pair<Integer, Integer> addedTile;
    private final Vector<Pair<Integer, Integer>> otherTiles;

    Move(Vector<Pair<Integer, Integer>> painted,
         Pair<Integer, Integer> addedTile,
         Vector<Pair<Integer, Integer>> otherTiles) {
        this.painted = painted;
        this.addedTile = addedTile;
        this.otherTiles = otherTiles;
    }

    public Pair<Integer, Integer> getAddedTile() {
        return addedTile;
    }

    public Vector<Pair<Integer, Integer>> getOtherTiles() {
        return otherTiles;
    }

    public Vector<Pair<Integer, Integer>> getPainted() {
        return painted;
    }
}
