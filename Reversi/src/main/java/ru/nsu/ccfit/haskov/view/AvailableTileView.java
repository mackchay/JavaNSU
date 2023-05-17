package ru.nsu.ccfit.haskov.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import ru.nsu.ccfit.haskov.model.Cell;
import java.util.Objects;

public class AvailableTileView {
    private final ImageView imageView;

    public AvailableTileView(int width, int height, Cell coords) {
        Image img = new Image(Objects.requireNonNull(FieldView.class.getResourceAsStream("green_circle.png")));
        imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setId(Integer.toString(coords.getRow()) + coords.getCol());
    }

    public ImageView getImageView() {
        return imageView;
    }

}
