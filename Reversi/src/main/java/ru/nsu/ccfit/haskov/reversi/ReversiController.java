package ru.nsu.ccfit.haskov.reversi;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import ru.nsu.ccfit.haskov.highscore.HighScoresApplication;
import ru.nsu.ccfit.haskov.highscore.HighScoresController;
import ru.nsu.ccfit.haskov.model.*;
import ru.nsu.ccfit.haskov.model.Bot;
import ru.nsu.ccfit.haskov.view.ReversiView;
import ru.nsu.ccfit.haskov.view.ViewData;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReversiController {
    private ReversiModel reversiModel;
    private ReversiView reversiView;
    public GridPane gridPane;
    public Text textBlack;
    public Text textWhite;
    public StackPane resultField;
    public Button exitButton;

    public Text blackTurn;

    public Text whiteTurn;

    public void initialize() {
        reversiModel = new ReversiModel();
        reversiView = new ReversiView(this, gridPane, textBlack, textWhite, resultField,
                blackTurn, whiteTurn);
    }

    @FXML
    private void exit() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
        Platform.exit();
    }

    @FXML
    public void restart() {
        reversiModel = new ReversiModel();
        reversiView.reset(this, gridPane, textBlack, textWhite);
    }

    public void showHighScores() throws Exception {
        HighScoresApplication highScoresApplication = new HighScoresApplication();
        highScoresApplication.start(new Stage());
    }

    public void showAbout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("about-game.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 720, 720);
        Text text = (Text) (root.lookup("#aboutBorderPane").lookup("#aboutText"));
        InputStream inputStream = ReversiController.class.getResourceAsStream("about.txt");
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            text.setText(text.getText() + '\n' + line);
        }
        Stage stage = new Stage();
        stage.setTitle("ABOUT");
        stage.setScene(scene);
        stage.show();
    }

    private void putBotChip() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            MoveResult botMove = reversiModel.moveBot();
            ViewData botViewData = new ViewData(
                    botMove.getMove().getPainted(),
                    botMove.getAvailableCells(),
                    botMove.getMove().getAddedTile(),
                    !botMove.getMove().getAddedTile().getCellColor().equals(
                            reversiModel.getCurrentPlayer().getCellColor()
                    ),
                    true
            );
            reversiView.updateView(botViewData,
                    botMove.getPlayerScore(),
                    botMove.getOpponentScore()
            );
            isGameOver();
            if (!reversiModel.isGameOver()
                    && reversiModel.getCurrentPlayer().getCellColor().equals(botMove.getMove().getAddedTile().getCellColor())) {
                delay.play();
            }
        });
        delay.play();
    }

    private void putPlayerChip(Cell cell) {
        MoveResult humanMove = reversiModel.moveHuman(cell);
        ViewData humanViewData = new ViewData(
                humanMove.getMove().getPainted(),
                humanMove.getAvailableCells(),
                humanMove.getMove().getAddedTile(),
                !humanMove.getMove().getAddedTile().getCellColor().equals(
                        reversiModel.getCurrentPlayer().getCellColor()
                ),
                false
        );
        reversiView.updateView(
                humanViewData,
                humanMove.getPlayerScore(),
                humanMove.getOpponentScore()
        );
        isGameOver();
    }

    @FXML
    public void putChip(Cell cell) {
        if (reversiModel.isAvailable(cell) && reversiView.isStatusView()) {
            putPlayerChip(cell);
            if (reversiModel.getCurrentPlayer() instanceof Bot) {
                putBotChip();
            }
        }
    }

    public void isGameOver() {
        if (reversiModel.isGameOver()) {
            reversiView.showResult(reversiModel.getWinner().equals(CellColor.BLACK));
            reversiModel.checkHighScores();
        }
    }
}
