package ru.nsu.ccfit.haskov.model.player;

import ru.nsu.ccfit.haskov.model.BoardModel;
import ru.nsu.ccfit.haskov.model.Cell;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.model.Move;

import java.util.Vector;

public class Player {
    protected final CellColor cellColor;
    protected int score;
    protected Vector<Cell> availableCells;

    public Player(CellColor cellColor, int score, Vector<Cell> availableCells) {
        this.cellColor = cellColor;
        this.score = score;
        this.availableCells = availableCells;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setAvailableCells(Vector<Cell> availableCells) {
        this.availableCells = availableCells;
    }
    public Vector<Cell> getAvailableCells() {
        return availableCells;
    }

    public Move makeMove(BoardModel boardModel, Cell cell) {
        cell.setCellColor(cellColor);
        Vector<Cell> painted = boardModel.updateFieldData(cell);
        availableCells = boardModel.checkAvailable(cellColor);
        score += painted.size();
        return new Move(painted, cell);
    }
}
