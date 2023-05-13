package ru.nsu.ccfit.haskov.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.Objects;

public class BlackChip implements Chip{
    private final ImageView imageView;

    public BlackChip(int width, int height, Pair<Integer, Integer> coords) {
        Image img;
        img = new Image(Objects.requireNonNull(FieldView.class.getResourceAsStream("black_chip3.png")));
        imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setId(coords.getKey().toString() + coords.getValue().toString() + "chip");
    }

    public ImageView getImageView() {
        return imageView;
    }
}
