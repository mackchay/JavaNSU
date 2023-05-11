package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class ReversiModel {
    private final Player human;
    private final Bot bot;
    private final static int startScore = 2;

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
    public Move moveBot() {
        Move move = bot.makeMove(fieldModel);
        bot.setScore(bot.getScore() + move.getPainted().size());
        human.setScore(human.getScore() - move.getPainted().size() + 1);
        setWinner();
        return move;
    }

    public Move moveHuman(int row, int col) {
        Move move = fieldModel.updateFieldData(human.getColor(), row, col);
        human.setScore(human.getScore() + move.getPainted().size());
        bot.setScore(bot.getScore() - move.getPainted().size() + 1);
        setWinner();
        return move;
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
