package ru.nsu.ccfit.haskov.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ru.nsu.ccfit.haskov.reversi.ReversiController;

import java.util.Objects;
import java.util.Vector;

public class FieldView {
    private final GridPane play_field;
    ReversiController controller;

    private Tiles tiles;
    private final static int field_size = 8;
    private final static int height = 80;
    private final static int width = 80;

    public FieldView(GridPane gridPane, ReversiController reversiController) {
        controller = reversiController;
        play_field = gridPane;
        tiles = new Tiles(field_size, field_size);
        for (int i = 0; i < field_size; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(height);
            play_field.getRowConstraints().add(row);
            for (int j = 0; j < field_size; j++) {
                ColumnConstraints col = new ColumnConstraints();
                col.setPrefWidth(width);
                play_field.getColumnConstraints().add(col);
                Button button = new Button();
                button.setMinSize(width, height);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(event -> controller.putChip(finalI, finalJ));
                if ((i + j) % 2 == 0) {
                    button.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                StackPane stackPane = new StackPane(button);
                play_field.add(stackPane, i, j);
                if ((i == field_size / 2 || i == field_size / 2 - 1) && (j == field_size / 2 || j == field_size / 2 - 1)) {
                    if (i == j) {
                        play_field.add(new BlackChip(width, height, new Pair<>(i, j)).getImageView(), i, j);
                    } else {
                        play_field.add(new WhiteChip(width, height, new Pair<>(i, j)).getImageView(), i, j);
                    }
                }
            }
        }
        for (Pair<Integer, Integer> availableTile : tiles.getAvailableTiles()) {
            play_field.add(new AvailableTileView(width, height,
                            new Pair<>(availableTile.getKey(), availableTile.getValue())).getImageView(),
                    availableTile.getKey(),
                    availableTile.getValue());
        }
    }

    public void updateFieldView(Tiles tiles) {
        removeAvailable();
        if (tiles.getColor() == 2) {
            setAvailable(tiles.getAvailableTiles());
        }
        this.tiles = tiles;
        setChips(tiles);
    }

    private void setChip(int color, int row, int col) {
        Chip chip;
        if (color == 1) {
            chip = new BlackChip(width, height, new Pair<>(row, col));
        } else {
            chip = new WhiteChip(width, height, new Pair<>(row, col));
        }
        play_field.add(chip.getImageView(), row, col);
    }

    private void setAvailable(Vector<Pair<Integer, Integer>> availableTiles) {
        for (Pair<Integer, Integer> availableTile : availableTiles) {
            Integer row = availableTile.getKey();
            Integer col = availableTile.getValue();
            AvailableTileView availableTileView = new AvailableTileView(width, height, new Pair<>(row, col));
            play_field.add(availableTileView.getImageView(), row, col);
        }
    }

    private void removeAvailable() {
        for (Pair<Integer, Integer> availableTile : tiles.getAvailableTiles()) {
            Integer row = availableTile.getKey();
            Integer col = availableTile.getValue();
            ImageView imageViewInCell = (ImageView) play_field.lookup("#" + row + col);
            play_field.getChildren().remove(imageViewInCell);
        }
    }

    private void setChips(Tiles tiles) {
        for (Pair<Integer, Integer> changeColorTiles : tiles.getChangeColorTiles()) {
            Integer row = changeColorTiles.getKey();
            Integer col = changeColorTiles.getValue();
            ImageView imageViewInCell = (ImageView) play_field.lookup("#" + row + col + "chip");
            play_field.getChildren().remove(imageViewInCell);
            this.setChip(tiles.getColor(), row, col);
        }
    }

    public void deleteField() {
        play_field.getChildren().clear();
    }


}
