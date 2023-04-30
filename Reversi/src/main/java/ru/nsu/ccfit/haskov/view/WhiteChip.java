package ru.nsu.ccfit.haskov.view;

import com.example.reversi.Field;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class WhiteChip {
    private final ImageView imageView;
    public WhiteChip(int width, int height) {
        Image img;
        img = new Image(Objects.requireNonNull(Field.class.getResourceAsStream("white_chip.png")));
        imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
