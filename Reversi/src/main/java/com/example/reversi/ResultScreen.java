package com.example.reversi;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class ResultScreen {

    StackPane stackPane;
    ResultScreen(StackPane stackPane) {
        this.stackPane = stackPane;
    }
    public void showWin() {
//        pane.getChildren().add(text);
//        pane.getChildren().add(imageView);
    }

    public void showLose() {
        ImageView imageView = new ImageView(
                new Image(Objects.requireNonNull(ResultScreen.class.getResourceAsStream("win.png"))));
        stackPane.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.CENTER);
    }
}
