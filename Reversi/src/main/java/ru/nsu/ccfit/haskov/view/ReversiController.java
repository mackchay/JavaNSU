package ru.nsu.ccfit.haskov.view;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.ccfit.haskov.model.ReversiModel;
import ru.nsu.ccfit.haskov.reversi.GameApplication;

import java.io.IOException;
import java.util.Vector;

public class ReversiController {
    private ReversiModel reversiModel;
    private ReversiView reversiView;
    @FXML
    public GridPane gridPane;
    public Text textBlack;
    public Text textWhite;
    public StackPane resultField;
    public Button exitButton;

    public void initialize() {
        reversiModel = new ReversiModel();
        reversiView = new ReversiView(this, gridPane, textBlack, textWhite, resultField);
    }
    @FXML
    private void exit() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    public void restart() {
        reversiModel = new ReversiModel();
        reversiView.reset(this, gridPane, textBlack, textWhite);
    }

    public void showHighScores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("high-scores.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 720, 720);
        Stage stage = new Stage();
        stage.setTitle("HIGHSCORES");
        stage.setScene(scene);
        stage.show();
    }

    public void showAbout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("about-game.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 720, 720);
        Stage stage = new Stage();
        stage.setTitle("ABOUT");
        stage.setScene(scene);
        stage.show();
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
                    reversiView.showResult(reversiModel.getWinner() == reversiModel.getHumanColor());
                }
            });
            delay.play();
        }
    }
}
