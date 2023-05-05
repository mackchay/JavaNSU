package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class ReversiModel {
    private final Player human;
    private final static int startScore = 2;
    private final Bot bot;

    private int winner = 0;
    private final FieldModel fieldModel;

    public ReversiModel() {
        fieldModel = new FieldModel();
        human = new Player(fieldModel.getColorA(), startScore);
        bot = new Bot(fieldModel.getColorB(), startScore);
    }

    public boolean isAvailable(int row, int col) {
        return fieldModel.isEmpty(row, col);
    }
    public Vector<Integer[]> moveBot() {
        Vector<Integer[]> vector = bot.makeMove(fieldModel);
        bot.setScore(bot.getScore() + vector.size());
        human.setScore(human.getScore() - vector.size() + 1);
        return vector;
    }

    public Vector<Integer[]> moveHuman(int row, int col) {
        Vector<Integer[]> vector = fieldModel.updateFieldData(human.getColor(), row, col);
        human.setScore(human.getScore() + vector.size());
        bot.setScore(bot.getScore() - vector.size() + 1);
        setWinner();
        return vector;
    }

    private void setWinner() {
        if (human.getScore() > bot.getScore()) {
            winner = human.getColor();
        }
        else {
            winner = bot.getColor();
        }
    }

    public int getWinner() {
        return winner;
    }

    public int getHumanColor() {
        return human.getColor();
    }

    public int getHumanScore() {
        return human.getScore();
    }

    public int getBotScore() {
        return bot.getScore();
    }

    public int getBotColor() {
        return bot.getColor();
    }

    public boolean isGameOver() {
        return (human.getScore() + bot.getScore()) == (fieldModel.getField_size() * fieldModel.getField_size());
    }
}
