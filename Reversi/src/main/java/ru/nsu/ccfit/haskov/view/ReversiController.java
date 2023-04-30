package ru.nsu.ccfit.haskov.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ru.nsu.ccfit.haskov.model.ReversiModel;
import com.example.reversi.Field;

public class ReversiController {
    private final static ReversiModel reversiModel = new ReversiModel();
    private final static Field field = new Field();
    @FXML
    public AnchorPane main;

    @FXML
    public void initialize() {

    }
    @FXML
    public void putChip(int row, int col) {
        if (reversiModel.moveHuman(row, col)) {
            int i = 0, j = 0;
            field.setBlackChip(row, col);
            while (!reversiModel.moveBot(row, col) && i < 8 && j < 8) {
                j++;
                i++;
            }
            field.setWhiteChip(row, col);
        }
        main.getChildren().add(field.getPane());
    }

}
