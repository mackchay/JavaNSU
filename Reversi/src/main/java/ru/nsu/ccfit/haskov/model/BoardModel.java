package ru.nsu.ccfit.haskov.model;

import javafx.util.Pair;

import java.util.Vector;

public class BoardModel {

    private final Cell[][] cells;
    private final static int size = 8;

    BoardModel() {
        cells = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row == size /2 || row == size /2 - 1) &&
                        (col == size /2 || col == size /2 - 1)) {
                    if (row == col) {
                        cells[row][col] = new Cell(row, col, CellColor.BLACK);
                    }
                    else {
                        cells[row][col] = new Cell(row, col, CellColor.WHITE);
                    }
                }
                else {
                    cells[row][col] = new Cell(row, col, CellColor.EMPTY);
                }
            }
        }
    }

    boolean isEmpty(int row, int col) {
        return cells[row][col].getCellColor().equals(CellColor.EMPTY);
    }

    public Vector<Cell> updateFieldData(Cell cell) {

        CellColor cellColor = cell.getCellColor();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Vector<Cell> globalChangeColorVector = new Vector<>();
        globalChangeColorVector.add(cells[cell.getRow()][cell.getCol()]);


        for (int[] direction : directions) {
            int r = cell.getRow() + direction[0];
            int c = cell.getCol() + direction[1];

            Vector<Cell> changeColorVector = new Vector<>();
            while (r >= 0 && r < size && c >= 0 && c < size
                    && !cells[r][c].getCellColor().equals(cellColor)) {
                if (!isEmpty(r, c)) {
                    changeColorVector.add(cells[r][c]);
                }
                r += direction[0];
                c += direction[1];
            }
            if (r >= 0 && r < size && c >= 0 && c < size
                    && cells[r][c].getCellColor().equals(cellColor)) {
                globalChangeColorVector.addAll(changeColorVector);
            }
        }

        for (Cell vectorElem: globalChangeColorVector) {
            vectorElem.setCellColor(cellColor);
        }
        return globalChangeColorVector;
    }

    private Vector<Cell> checkAvailableFromCell(Cell cell) {
        Vector<Cell> availableCells = new Vector<>();
        CellColor cellColor = cell.getCellColor();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int r = cell.getRow() + direction[0];
            int c = cell.getCol() + direction[1];
            int enemiesCount = 0;
            while (r >= 0 && r < size && c >= 0 && c < size &&
                    !cells[r][c].getCellColor().equals(CellColor.EMPTY)
                    && !cells[r][c].getCellColor().equals(cellColor)) {
                if (!cells[r][c].getCellColor().equals(CellColor.EMPTY)) {
                    enemiesCount++;
                }
                r += direction[0];
                c += direction[1];
            }
            if (r >= 0 && r < size && c >= 0 && c < size &&
                    cells[r][c].getCellColor().equals(CellColor.EMPTY) && enemiesCount > 0) {
                availableCells.add(cells[r][c]);
            }
        }
        return availableCells;
    }
    public Vector<Cell> checkAvailable(CellColor cellColor) {
        Vector<Cell> availableCells = new Vector<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getCellColor().equals(cellColor)) {
                    availableCells.addAll(checkAvailableFromCell(cells[i][j]));
                }
            }
        }
        return availableCells;
    }
}
