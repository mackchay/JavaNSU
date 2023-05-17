package ru.nsu.ccfit.haskov.model.player;


import ru.nsu.ccfit.haskov.model.BoardModel;
import ru.nsu.ccfit.haskov.model.Cell;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.model.Move;

import java.util.Vector;

public class Bot extends Player {

    public Bot(CellColor cellColor,
               int score,
               Vector<Cell> availableCells) {
        super(cellColor, score, availableCells);
    }

    public Move makeMove(BoardModel boardModel) {
        return makeMove(boardModel, getCell(boardModel));
    }

    public Cell getCell(BoardModel boardModel) {
        int index = (int)(Math.random() * availableCells.size());
        return availableCells.get(index);
    }
}
