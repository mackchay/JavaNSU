package ru.nsu.ccfit.haskov.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class WinScreen extends ResultScreen {

    WinScreen(StackPane stackPane) {
        super(stackPane, new ImageView(
                new Image(Objects.requireNonNull(ResultScreen.class.getResourceAsStream("win.png")))));
    }
}
