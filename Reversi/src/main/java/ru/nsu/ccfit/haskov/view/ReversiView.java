package ru.nsu.ccfit.haskov.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.Vector;

public class ReversiView {
    private FieldView fieldView;
    private ScoreBoard scoreBlack;
    private ScoreBoard scoreWhite;

    private ResultScreen resultScreen;
    private final StackPane stackPane;
    private boolean status;

    public ReversiView(ReversiController reversiController,
                       GridPane gridPane, Text textBlack, Text textWhite, StackPane stackPane) {
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        this.stackPane = stackPane;
        status = true;
    }

    public void reset(ReversiController reversiController,
    GridPane gridPane, Text textBlack, Text textWhite) {
        if (!Objects.isNull(resultScreen)) {
            resultScreen.hide();
        }
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        status = true;
    }

    public void updateView(Vector<Integer[]> changeColorVector,
                           int color, int thisValue, int otherValue) {
        fieldView.updateFieldView(changeColorVector, color);
        if (color == 1) {
            scoreBlack.setTextField(thisValue);
            scoreWhite.setTextField(otherValue);
        } else {
            scoreBlack.setTextField(otherValue);
            scoreWhite.setTextField(thisValue);
        }
        status = !status;
    }
    private void setScoreValue(int value, int color) {
        if (color == 1) {
            scoreBlack.setTextField(value);
        } else {
            scoreWhite.setTextField(value);
        }
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
    }
}
