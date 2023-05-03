package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class FieldModel {

    private final int[][] field_data;
    private final static int field_size = 8;

    private final static int empty = 0;
    private final static int colorA = 1;
    private final static int colorB = 2;

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

    public Vector<Integer[]> updateFieldData(int color, int row, int col) {

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Vector<Integer[]> globalChangeColorVector = new Vector<>();
        field_data[row][col] = color;
        globalChangeColorVector.add(new Integer[]{row, col});

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];

            Vector<Integer[]> changeColorVector = new Vector<>();
            while (r >= 0 && r < field_size && c >= 0 && c < field_size && field_data[r][c] != color) {
                if (!isEmpty(r, c)) {
                    changeColorVector.add(new Integer[]{r, c});
                }
                r += direction[0];
                c += direction[1];
            }
            if (r >= 0 && r < field_size && c >= 0 && c < field_size && field_data[r][c] == color) {
                for (Integer []vectorElem: changeColorVector) {
                    this.setTile(color, vectorElem[0], vectorElem[1]);
                }
                globalChangeColorVector.addAll(changeColorVector);
            }
        }


        return globalChangeColorVector;
    }
}
