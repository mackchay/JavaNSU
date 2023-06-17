package ru.nsu.ccfit.haskov.highscore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.nsu.ccfit.haskov.reversi.GameApplication;
import ru.nsu.ccfit.haskov.reversi.ReversiController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HighScoresApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HighScoresApplication.class.getResource("high-scores.fxml"));
        AnchorPane root = fxmlLoader.load();

        FileReader file = new FileReader( "src/main/files/high-scores.txt");
        String line;
        int iter = 0, layoutY = 0;
        BufferedReader bufferedReader = new BufferedReader(file);
        while ((line = bufferedReader.readLine()) != null && iter < 5) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(HighScoresApplication.class.getResource("score-pattern.fxml"));
            AnchorPane pattern = fxmlLoader1.load();
            pattern.setLayoutY(layoutY);
            root.getChildren().add(pattern);
            String[] data = line.split(" ");
            Text textBlack = (Text) pattern.lookup("#textBlack");
            textBlack.setText(data[0]);
            Text textWhite = (Text) pattern.lookup("#textWhite");
            textWhite.setText(data[1]);
            iter++;
            layoutY += 80;
        }
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("HIGHSCORES");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
