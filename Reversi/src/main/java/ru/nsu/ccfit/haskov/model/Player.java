package ru.nsu.ccfit.haskov.model;

import ru.nsu.ccfit.haskov.model.BoardModel;
import ru.nsu.ccfit.haskov.model.Cell;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.model.Move;

import java.util.Vector;

public class Player {
    private final CellColor cellColor;
    private int score;
    private Vector<Cell> availableCells;

    protected Player(CellColor cellColor, int score, Vector<Cell> availableCells) {
        this.cellColor = cellColor;
        this.score = score;
        this.availableCells = availableCells;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    protected void setScore(int score) {
        this.score = score;
    }
    protected int getScore() {
        return score;
    }

    protected void setAvailableCells(Vector<Cell> availableCells) {
        this.availableCells = availableCells;
    }
    protected Vector<Cell> getAvailableCells() {
        return availableCells;
    }

    protected Move makeMove(BoardModel boardModel, Cell cell) {
        cell.setCellColor(cellColor);
        Vector<Cell> painted = boardModel.updateFieldData(cell);
        availableCells = boardModel.checkAvailable(cellColor);
        score += painted.size();
        return new Move(painted, cell);
    }
}
