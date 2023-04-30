package ru.nsu.ccfit.haskov.model;

public class ReversiModel {
    private final Player human;
    private final Player bot;
    private final static int empty = 0;
    private final FieldModel fieldModel;

    public ReversiModel() {
        fieldModel = new FieldModel();
        human = new Player(fieldModel.getColorA());
        bot = new Player(fieldModel.getColorB());
    }

    public boolean moveBot(int row, int col) {
        if (fieldModel.isEmpty(row, col)) {
            return false;
        }
        fieldModel.setField_data(human.getColor(), row, col);
        return true;
    }

    public boolean moveHuman(int row, int col) {
        if (fieldModel.isEmpty(row, col)) {
            return false;
        }
        fieldModel.setField_data(human.getColor(), row, col);
        return true;
    }
}
