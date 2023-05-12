package ru.nsu.ccfit.haskov.view;

import javafx.util.Pair;

import java.util.Vector;

public class Tiles {
    private final Vector<Pair<Integer, Integer>> changeColorTiles;
    private final Vector<Pair<Integer, Integer>> availableTiles;
    private final Pair<Integer, Integer> newTile;
    private final Vector<Pair<Integer, Integer>> usedTiles;

    private final int color;

    public Tiles(Vector<Pair<Integer, Integer>> changeColorTiles,
                 Vector<Pair<Integer, Integer>> availableTiles,
                 Pair<Integer, Integer> newTile,
                 Vector<Pair<Integer, Integer>> usedTiles,
                 int color) {
        this.changeColorTiles = changeColorTiles;
        this.newTile = newTile;
        this.usedTiles = usedTiles;
        this.availableTiles = availableTiles;
        this.color = color;
    }

    public Vector<Pair<Integer, Integer>> getChangeColorTiles() {
        return changeColorTiles;
    }

    public Pair<Integer, Integer> getNewTile() {
        return newTile;
    }
    public Vector<Pair<Integer, Integer>> getUsedTiles() {
        return usedTiles;
    }
    public Vector<Pair<Integer, Integer>> getAvailableTiles() {
        return availableTiles;
    }

    public int getColor() {
        return color;
    }
}
