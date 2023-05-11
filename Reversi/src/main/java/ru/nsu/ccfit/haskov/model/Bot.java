package ru.nsu.ccfit.haskov.model;


import javafx.util.Pair;

import java.util.Vector;

public class Bot extends Player{

    public Bot(int color, int score) {
        super(color, score);
    }

    public Move makeMove(FieldModel fieldModel) {
        int index = (int)(Math.random() * fieldModel.getAvailTiles(color).size());
        return fieldModel.updateFieldData(color, fieldModel.getAvailTiles(color).get(index).getKey(),
                fieldModel.getAvailTiles(color).get(index).getValue());
    }
}
