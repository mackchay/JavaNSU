package ru.nsu.ccfit.haskov.view;

import com.example.reversi.FieldView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class WhiteChip implements Chip{
    private final ImageView imageView;
    public WhiteChip(int width, int height) {
        Image img;
        img = new Image(Objects.requireNonNull(FieldView.class.getResourceAsStream("white_chip.png")));
        imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setId("ChipImage");
    }

    public ImageView getImageView() {
        return imageView;
    }
}
