package ru.nsu.ccfit.haskov.model;


import java.util.Vector;

public class Bot extends Player {

    protected Bot(CellColor cellColor,
               int score,
               Vector<Cell> availableCells) {
        super(cellColor, score, availableCells);
    }

    protected Move makeMove(BoardModel boardModel) {
        return makeMove(boardModel, getCell(boardModel));
    }

    protected Cell getCell(BoardModel boardModel) {
        int index = (int)(Math.random() * getAvailableCells().size());
        return getAvailableCells().get(index);
    }
}
