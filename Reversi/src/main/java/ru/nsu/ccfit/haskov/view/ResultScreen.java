package ru.nsu.ccfit.haskov.view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class ResultScreen {
    StackPane stackPane;
    ImageView imageView;

    ResultScreen(StackPane pane, ImageView imageView) {
        this.stackPane = pane;
        this.imageView = imageView;
    }

    public void show() {
        stackPane.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.CENTER);
    }

    public void hide() {
        stackPane.getChildren().remove(imageView);
    }
}
