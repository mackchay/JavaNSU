package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class MoveResult {
    private final Move move;

    private final Vector<Cell> availableCells;

    private final int playerScore;
    private final int opponentScore;

    MoveResult(Move move,
               Vector<Cell> availableCells,
               int playerScore,
               int opponentScore) {
        this.availableCells = availableCells;
        this.opponentScore = opponentScore;
        this.move = move;
        this.playerScore = playerScore;
    }

    public Vector<Cell> getAvailableCells() {
        return availableCells;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public Move getMove() {
        return move;
    }
}
