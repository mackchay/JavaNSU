package ru.nsu.ccfit.haskov.model;

public class Player {
    protected int color;
    protected int score;

    Player(int color, int score) {
        this.color = color;
        this.score = score;
    }

    int getColor() {
        return color;
    }

    void setScore(int score) {
        this.score = score;
    }
    int getScore() {
        return score;
    }
}
