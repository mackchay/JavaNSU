package ru.nsu.ccfit.haskov.model;

import java.util.Vector;

public class ReversiModel {
    private final Player player1;
    private final Player player2;
    private final static int startScore = 2;
    private CellColor winner = CellColor.EMPTY;
    private final BoardModel boardModel;
    private CellColor turn;

    public ReversiModel() {
        boardModel = new BoardModel();
        player1 = new Player(CellColor.BLACK, startScore, boardModel.checkAvailable(CellColor.BLACK));
        player2 = new Bot(CellColor.WHITE, startScore, boardModel.checkAvailable(CellColor.WHITE));
        turn = CellColor.BLACK;
    }

    public Player getCurrentPlayer() {
        if (turn.equals(player1.getCellColor())) {
            return player1;
        }
        return player2;
    }

    public Player getOpponent() {
        if (!turn.equals(player1.getCellColor())) {
            return player1;
        }
        return player2;
    }

    public boolean isAvailable(Cell cell) {
        return getCurrentPlayer().getAvailableCells().contains(cell);
    }

    public MoveResult moveBot() {
        Bot bot = (Bot) getCurrentPlayer();
        Move move = bot.makeMove(boardModel);
        return result(move);
    }

    private MoveResult result(Move move) {
        Player opponent = getOpponent();
        Player player = getCurrentPlayer();
        opponent.setScore(getOpponent().getScore() - move.getPainted().size() + 1);
        opponent.setAvailableCells(boardModel.checkAvailable(opponent.getCellColor()));
        Vector<Cell> availableCells;
        if (opponent.getAvailableCells().size() > 0) {
            availableCells = opponent.getAvailableCells();
        }
        else {
            availableCells = player.getAvailableCells();
        }
        MoveResult moveResult = new MoveResult(move,
                availableCells,
                player.getScore(),
                opponent.getScore()
        );
        finalizePlayerMove();
        return moveResult;
    }

    public MoveResult moveHuman(Cell cell) {
        Move move = getCurrentPlayer().makeMove(boardModel, cell);
        return result(move);
    }

    private void setWinner() {
        if (player1.getScore() > player2.getScore()) {
            winner = player1.getCellColor();
        } else {
            winner = player2.getCellColor();
        }
    }

    public CellColor getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return player1.getAvailableCells().size() == 0 && player2.getAvailableCells().size() == 0;
    }

    private void finalizePlayerMove() {
        Player opponent = getOpponent();
        if (opponent.getAvailableCells().size() > 0) {
            turn = opponent.getCellColor();
        }
        setWinner();
    }

    public void checkHighScores() {
        HighScoreBoard highScoreBoard = new HighScoreBoard();
        highScoreBoard.addRecord(player1.getScore(), player2.getScore());
    }
}
