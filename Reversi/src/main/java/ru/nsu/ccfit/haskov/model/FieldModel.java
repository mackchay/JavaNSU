package ru.nsu.ccfit.haskov.model;

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

    void setField_data(int color, int row, int col) {
        field_data[row][col] = color;
    }
}
