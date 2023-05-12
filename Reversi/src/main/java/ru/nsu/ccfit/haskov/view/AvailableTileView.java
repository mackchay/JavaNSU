package ru.nsu.ccfit.haskov.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AvailableTileView {
    private final ImageView imageView;

    public AvailableTileView(int width, int height) {
        Image img;
        img = new Image(Objects.requireNonNull(FieldView.class.getResourceAsStream("green_circle.png")));
        imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setId("GreenCircleImage");
    }

    public ImageView getImageView() {
        return imageView;
    }

}
