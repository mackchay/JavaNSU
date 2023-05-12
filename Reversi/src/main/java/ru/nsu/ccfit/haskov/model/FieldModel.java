package ru.nsu.ccfit.haskov.model;

import javafx.util.Pair;

import java.util.Vector;

public class FieldModel {

    private final int[][] field_data;
    private final static int field_size = 8;

    private final static int empty = 0;
    private final static int colorA = 1;
    private final static int colorB = 2;
    private Vector<Pair<Integer, Integer>> availATiles;
    private Vector<Pair<Integer, Integer>> availBTiles;

    FieldModel() {
        field_data = new int[field_size][field_size];
        for (int row = 0; row < field_size; row++) {
            for (int col = 0; col < field_size; col++) {
                if ((row == field_size /2 || row == field_size /2 - 1) &&
                        (col == field_size /2 || col == field_size /2 - 1)) {
                    if (row == col) {
                        field_data[row][col] = colorA;
                    }
                    else {
                        field_data[row][col] = colorB;
                    }
                }
                else {
                    field_data[row][col] = empty;
                }
            }
        }
        checkAvailableTiles();
    }

    int getColorA() {
        return colorA;
    }

    int getColorB() {
        return colorB;
    }

    boolean isEmpty(int row, int col) {
        return field_data[row][col] == empty;
    }

    private void setTile(int color, int row, int col) {
        field_data[row][col] = color;
    }

    int getField_size() {
        return field_size;
    }

    public Move updateFieldData(int color, int row, int col) {

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Vector<Pair<Integer, Integer>> globalChangeColorVector = new Vector<>();
        Vector<Pair<Integer, Integer>> otherTilesVector = new Vector<>();
        field_data[row][col] = color;
        globalChangeColorVector.add(new Pair<>(row, col));


        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];

            Vector<Pair<Integer, Integer>> changeColorVector = new Vector<>();
            while (r >= 0 && r < field_size && c >= 0 && c < field_size && field_data[r][c] != color) {
                if (!isEmpty(r, c)) {
                    changeColorVector.add(new Pair<Integer, Integer>(r, c));
                }
                r += direction[0];
                c += direction[1];
            }
            if (r >= 0 && r < field_size && c >= 0 && c < field_size && field_data[r][c] == color) {
                for (Pair<Integer, Integer> vectorElem: changeColorVector) {
                    this.setTile(color, vectorElem.getKey(), vectorElem.getValue());
                }
                otherTilesVector.add(new Pair<>(r, c));
                globalChangeColorVector.addAll(changeColorVector);
            }
        }

        Move move = new Move(globalChangeColorVector, new Pair<>(row, col), otherTilesVector);
        checkAvailableTiles();
        return move;
    }

    private void checkAvailableTilesByTile(int row, int col) {
        int color = field_data[row][col];
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            int enemiesCount = 0;
            while (r >= 0 && r < field_size && c >= 0 && c < field_size &&
                    field_data[r][c] != empty && field_data[r][c] != field_data[row][col]) {
                if (color != field_data[r][c]) {
                    enemiesCount++;
                }
                r += direction[0];
                c += direction[1];
            }
            if (r >= 0 && r < field_size && c >= 0 && c < field_size &&
                    field_data[r][c] == empty && enemiesCount > 0) {
                if (color == colorA) {
                    availATiles.add(new Pair<>(r, c));
                }
                else {
                    availBTiles.add(new Pair<>(r, c));
                }
            }
        }
    }
    private void checkAvailableTiles() {
        availATiles = new Vector<>();
        availBTiles = new Vector<>();
        for (int i = 0; i < field_size; i++) {
            for (int j = 0; j < field_size; j++) {
                if (field_data[i][j] != empty) {
                    checkAvailableTilesByTile(i, j);
                }
            }
        }
    }

    public Vector<Pair<Integer, Integer>> getAvailTiles(int color) {
        if (color == colorA) {
            return availATiles;
        }
        return availBTiles;
    }
}
