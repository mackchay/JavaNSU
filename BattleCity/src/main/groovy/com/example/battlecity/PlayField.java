package com.example.battlecity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class PlayField extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/example/battlecity/play_field.fxml")));
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setTitle("Battle City");
        stage.setScene(scene);
        stage.show();
    }
}
