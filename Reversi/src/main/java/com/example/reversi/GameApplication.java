package com.example.reversi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-start.fxml"));
        Parent root = fxmlLoader.load();
        Pane pane = (Pane) fxmlLoader.getNamespace().get("play_field");
        Field field = new Field(pane);

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("REVERSI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}