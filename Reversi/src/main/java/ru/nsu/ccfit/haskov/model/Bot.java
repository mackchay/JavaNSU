package ru.nsu.ccfit.haskov.model;


import java.util.Vector;

public class Bot extends Player{

    public Bot(int color, int score) {
        super(color, score);
    }

    public Move makeMove(FieldModel fieldModel) {
        for (int row = 0; row < fieldModel.getField_size(); row += fieldModel.getField_size() - 1) {
            for (int col = 0; col < fieldModel.getField_size(); col += fieldModel.getField_size() - 1) {
                if (fieldModel.isEmpty(row, col)) {
                    return fieldModel.updateFieldData(color, row, col);
                }
            }
        }

        Vector<Integer[]> emptyPlaces = new Vector<>();
        for (int row = 0; row < fieldModel.getField_size(); row += 1) {
            for (int col = 0; col < fieldModel.getField_size(); col += 1) {
                if (fieldModel.isEmpty(row, col)) {
                    emptyPlaces.add(new Integer[]{row, col});
                }
            }
        }

        int index = (int)(Math.random() * emptyPlaces.size());
        return fieldModel.updateFieldData(color, emptyPlaces.get(index)[0], emptyPlaces.get(index)[1]);
    }
}
