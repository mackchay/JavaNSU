package ru.nsu.ccfit.haskov.view;

import com.example.reversi.ReversiView;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ru.nsu.ccfit.haskov.model.ReversiModel;

import java.util.Vector;

public class ReversiController {
    private ReversiModel reversiModel;
    private ReversiView reversiView;
    @FXML
    public GridPane gridPane;
    public Text textBlack;
    public Text textWhite;
    public StackPane resultField;

    public void initialize() {
        reversiModel = new ReversiModel();
        reversiView = new ReversiView(this, gridPane, textBlack, textWhite, resultField);
    }
    @FXML
    public void putChip(int row, int col) {
        if (reversiModel.isAvailable(row, col) && reversiView.isStatusView()) {
            Vector<Integer[]> vectorHuman = reversiModel.moveHuman(row, col);
            reversiView.updateView(vectorHuman, reversiModel.getHumanColor(),
                    reversiModel.getHumanScore(), reversiModel.getBotScore());
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished( event -> {
                Vector<Integer[]> vectorBot = reversiModel.moveBot();
                reversiView.updateView(vectorBot, reversiModel.getBotColor(),
                        reversiModel.getBotScore(), reversiModel.getHumanScore());
                if (reversiModel.isGameOver()) {
                    System.out.println("Yahooo");
                    if (reversiModel.getWinner() == reversiModel.getHumanColor()) {
                        reversiView.showResult(true);
                    }
                }
            });
            delay.play();
        }
    }
}
