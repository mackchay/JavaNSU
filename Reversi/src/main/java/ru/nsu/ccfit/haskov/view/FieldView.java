package ru.nsu.ccfit.haskov.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ru.nsu.ccfit.haskov.model.Cell;
import ru.nsu.ccfit.haskov.model.CellColor;
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
                button.setOnAction(event -> controller.putChip(new Cell(finalI, finalJ)));
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
        for (Cell availableTile : tiles.getAvailableTiles()) {
            play_field.add(new AvailableTileView(width, height,
                            new Cell(availableTile.getRow(), availableTile.getCol())).getImageView(),
                    availableTile.getRow(),
                    availableTile.getCol());
        }
    }

    public void updateFieldView(Tiles tiles) {
        removeAvailable();
        if (tiles.isShowStatus()) {
            setAvailable(tiles.getAvailableTiles());
        }
        this.tiles = tiles;
        setChips(tiles);
    }

    private void setChip(Cell cell) {
        Chip chip;
        int row = cell.getRow();
        int col = cell.getCol();
        if (cell.getCellColor().equals(CellColor.BLACK)) {
            chip = new BlackChip(width, height, new Pair<>(row, col));
        } else {
            chip = new WhiteChip(width, height, new Pair<>(row, col));
        }
        play_field.add(chip.getImageView(), row, col);
    }

    private void setAvailable(Vector<Cell> availableTiles) {
        for (Cell availableTile : availableTiles) {
            int row = availableTile.getRow();
            int col = availableTile.getCol();
            AvailableTileView availableTileView = new AvailableTileView(width, height, new Cell(row, col));
            play_field.add(availableTileView.getImageView(), row, col);
        }
    }

    private void removeAvailable() {
        for (Cell availableTile : tiles.getAvailableTiles()) {
            int row = availableTile.getRow();
            int col = availableTile.getCol();
            ImageView imageViewInCell = (ImageView) play_field.lookup("#" +
                    row + col);
            play_field.getChildren().remove(imageViewInCell);
        }
    }

    private void setChips(Tiles tiles) {
        for (Cell changedColorTile : tiles.getChangeColorTiles()) {
            ImageView imageViewInCell = (ImageView) play_field.lookup(
                    "#" + Integer.toString(changedColorTile.getRow())
                            + Integer.toString(changedColorTile.getCol()) + "chip"
            );
            play_field.getChildren().remove(imageViewInCell);
            this.setChip(changedColorTile);
        }
    }

    public void deleteField() {
        play_field.getChildren().clear();
    }


}
