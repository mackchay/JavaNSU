package ru.nsu.ccfit.haskov.view;

import ru.nsu.ccfit.haskov.model.Cell;

import java.util.Vector;

public class ViewData {
    private final Vector<Cell> changeColorTiles;
    private final Vector<Cell> availableTiles;
    private final Cell newTile;

    private final boolean changePlayer;
    private final boolean showStatus;

    public ViewData(int width, int height) {
        newTile = new Cell(0, 0);
        changeColorTiles = new Vector<>();
        availableTiles = new Vector<>();
        availableTiles.add(new Cell(width/2 - 2, height/2));
        availableTiles.add(new Cell(width/2 + 1, height/2 - 1));
        availableTiles.add(new Cell(width/2 - 1, height/2 + 1));
        availableTiles.add(new Cell(width/2, height/2 - 2));
        showStatus = true;
        changePlayer = true;
    }
    public ViewData(Vector<Cell> changeColorTiles,
                    Vector<Cell> availableTiles,
                    Cell newTile,
                    boolean changePlayer, boolean showStatus) {
        this.changeColorTiles = changeColorTiles;
        this.newTile = newTile;
        this.availableTiles = availableTiles;
        this.changePlayer = changePlayer;
        this.showStatus = showStatus;
    }

    public Cell getNewTile() {
        return newTile;
    }

    public Vector<Cell> getChangeColorTiles() {
        return changeColorTiles;
    }

    public Vector<Cell> getAvailableTiles() {
        return availableTiles;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public boolean isChangePlayer() {
        return changePlayer;
    }

    public boolean getStatusValue() {
        return this.isShowStatus() == this.isChangePlayer();
    }
}
