package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class ReversiModel {
    private final Player human;
    private final Bot bot;
    private final FieldModel fieldModel;

    public ReversiModel() {
        fieldModel = new FieldModel();
        human = new Player(fieldModel.getColorA());
        bot = new Bot(fieldModel.getColorB());
    }

    public boolean isAvailable(int row, int col) {
        return fieldModel.isEmpty(row, col);
    }
    public Vector<Integer[]> moveBot() {
        return bot.makeMove(fieldModel);
    }

    public Vector<Integer[]> moveHuman(int row, int col) {
        return fieldModel.updateFieldData(human.getColor(), row, col);
    }

    public int getHumanColor() {
        return human.getColor();
    }

    public int getBotColor() {
        return bot.getColor();
    }
}
