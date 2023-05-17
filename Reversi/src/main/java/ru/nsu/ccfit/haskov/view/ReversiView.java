package ru.nsu.ccfit.haskov.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.reversi.ReversiController;

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
                       GridPane gridPane,
                       Text textBlack,
                       Text textWhite,
                       StackPane stackPane) {
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
        fieldView.deleteField();
        fieldView = new FieldView(gridPane, reversiController);
        scoreBlack = new ScoreBoard(textBlack);
        scoreWhite = new ScoreBoard(textWhite);
        status = true;
    }

    public void updateView(Tiles tiles, int thisValue, int otherValue) {
        fieldView.updateFieldView(tiles);
        if (tiles.getNewTile().getCellColor().equals(CellColor.BLACK)) {
            scoreBlack.setTextField(thisValue);
            scoreWhite.setTextField(otherValue);
        } else {
            scoreBlack.setTextField(otherValue);
            scoreWhite.setTextField(thisValue);
        }
        if (!tiles.isShowStatus()) {
            status = !status;
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
