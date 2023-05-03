package com.example.reversi;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import ru.nsu.ccfit.haskov.view.ReversiController;

import java.util.Vector;

public class ReversiView {
    FieldView fieldView;
    ScoreBoard scoreBlack;
    ScoreBoard scoreWhite;

    public ReversiView(ReversiController reversiController,
                       GridPane gridPane, Text textBlack, Text textWhite) {
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
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
    }
    private void setScoreValue(int value, int color) {
        if (color == 1) {
            scoreBlack.setTextField(value);
        } else {
            scoreWhite.setTextField(value);
        }
    }
}
