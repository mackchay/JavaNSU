package ru.nsu.ccfit.haskov.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.nsu.ccfit.haskov.model.ReversiModel;
import com.example.reversi.Field;

import java.util.Vector;

public class ReversiController {
    private ReversiModel reversiModel;
    private Field reversiView;
    @FXML
    public GridPane gridPane;

    public void initialize() {
        reversiModel = new ReversiModel();
        reversiView = new Field(gridPane, this);
    }
    @FXML
    public void putChip(int row, int col) {
        if (reversiModel.isAvailable(row, col)) {
            reversiView.updateView(reversiModel.moveHuman(row, col), reversiModel.getHumanColor());
            reversiView.updateView(reversiModel.moveBot(), reversiModel.getBotColor());
        }
    }
}
