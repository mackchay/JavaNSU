package ru.nsu.ccfit.haskov.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.reversi.ReversiController;

import java.util.Objects;

public class ReversiView {
    private FieldView fieldView;
    private ScoreBoard scoreBlack;
    private ScoreBoard scoreWhite;

    private HighScoreBoard highScoreBoard;
    private final TurnView turnView;
    private ResultScreen resultScreen;
    private final StackPane stackPane;
    private boolean status;

    public ReversiView(ReversiController reversiController,
                       GridPane gridPane,
                       Text textBlack,
                       Text textWhite,
                       StackPane stackPane,
                       Text blackTurn,
                       Text whiteTurn) {
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        this.stackPane = stackPane;
        status = true;
        turnView = new TurnView(blackTurn, whiteTurn);
        turnView.setTurnView(CellColor.WHITE);
    }

    public void reset(ReversiController reversiController,
    GridPane gridPane, Text textBlack, Text textWhite) {
        if (!Objects.isNull(resultScreen)) {
            resultScreen.hide();
        }
        fieldView.deleteField();
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        status = true;
        turnView.setTurnView(CellColor.WHITE);
    }

    public void updateView(ViewData viewData, int thisValue, int otherValue) {
        fieldView.updateFieldView(viewData);
        if (viewData.getNewTile().getCellColor().equals(CellColor.BLACK)) {
            scoreBlack.setTextField(thisValue);
            scoreWhite.setTextField(otherValue);
        } else {
            scoreBlack.setTextField(otherValue);
            scoreWhite.setTextField(thisValue);
        }
        if (viewData.isChangePlayer()) {
            turnView.setTurnView(viewData.getNewTile().getCellColor());
        }
        status = viewData.getStatusValue();
    }

    public boolean isStatusView() {
        return status;
    }

    public void showResult(boolean victory) {
        fieldView.deleteField();
        if (victory) {
            resultScreen = new WinScreen(stackPane);
            resultScreen.show();
        }
        else {
            resultScreen = new LoseScreen(stackPane);
            resultScreen.show();
        }
        highScoreBoard.addRecord(Integer.getInteger(scoreBlack.getText()), Integer.getInteger(scoreBlack.getText()));
    }
}
