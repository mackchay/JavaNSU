package ru.nsu.ccfit.haskov.model;

import java.util.Objects;

public class Cell {
    private final int row;
    private final int col;

    private CellColor cellColor = CellColor.EMPTY;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, CellColor cellColor) {
        this(row, col);
        this.cellColor = cellColor;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCellColor(CellColor cellColor) {
        this.cellColor = cellColor;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return row == cell.row && col == cell.col && cellColor == cell.cellColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, cellColor);
    }
}
