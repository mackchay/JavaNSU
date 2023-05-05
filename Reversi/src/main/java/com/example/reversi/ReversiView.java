package com.example.reversi;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import ru.nsu.ccfit.haskov.view.ReversiController;

import java.util.Vector;

public class ReversiView {
    private final FieldView fieldView;
    private final ScoreBoard scoreBlack;
    private final ScoreBoard scoreWhite;

    private final ResultScreen resultScreen;

    private boolean status;

    public ReversiView(ReversiController reversiController,
                       GridPane gridPane, Text textBlack, Text textWhite, StackPane stackPane) {
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        resultScreen = new ResultScreen(stackPane);
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
            resultScreen.showLose();
        }
    }
}
