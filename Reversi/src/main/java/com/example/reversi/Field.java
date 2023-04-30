package com.example.reversi;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.util.Objects;

public class Field {
    private final Pane play_field;
    private final static int size = 8;
    private final static int height = 80;
    private final static int width = 80;

    Field(Pane pane) {
        play_field = pane;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle rectangle = new Rectangle(width, height);
                rectangle.setLayoutX(i * width);
                rectangle.setLayoutY(j * height);
                if ((i + j) % 2 == 0) {
                    rectangle.setFill(Color.BLACK);
                }
                else {
                    rectangle.setFill(Color.WHITE);
                }
                if ((i == size/2 || i == size/2 + 1) && (j == size/2 || j == size/2 + 1)) {
                    Image img = new Image(Objects.requireNonNull(Field.class.getResourceAsStream("white_figure.png")));
                    ImageView imageView = new ImageView(img);
                    imageView.setLayoutX(i * width);
                    imageView.setLayoutY(j * height);
                    imageView.setFitWidth(width);
                    imageView.setFitHeight(height);
                    play_field.getChildren().add(imageView);
                }
                play_field.getChildren().add(rectangle);
            }
        }
    }

    Pane getPane() {
        return play_field;
    }
}
