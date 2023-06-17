package ru.nsu.ccfit.haskov.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ru.nsu.ccfit.haskov.model.Cell;
import ru.nsu.ccfit.haskov.model.CellColor;
import ru.nsu.ccfit.haskov.reversi.ReversiController;

import java.util.Vector;

public class FieldView {
    private final GridPane playField;
    ReversiController controller;


    private ViewData viewData;
    private final static int fieldSize = 8;
    private final static int height = 80;
    private final static int width = 80;

    public FieldView(GridPane gridPane, ReversiController reversiController) {
        controller = reversiController;
        playField = gridPane;
        viewData = new ViewData(fieldSize, fieldSize);
        for (int i = 0; i < fieldSize; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(height);
            playField.getRowConstraints().add(row);
            for (int j = 0; j < fieldSize; j++) {
                ColumnConstraints col = new ColumnConstraints();
                col.setPrefWidth(width);
                playField.getColumnConstraints().add(col);
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
                playField.add(stackPane, i, j);
                if ((i == fieldSize / 2 || i == fieldSize / 2 - 1) && (j == fieldSize / 2 || j == fieldSize / 2 - 1)) {
                    if (i == j) {
                        playField.add(new BlackChip(width, height, new Pair<>(i, j)).getImageView(), i, j);
                    } else {
                        playField.add(new WhiteChip(width, height, new Pair<>(i, j)).getImageView(), i, j);
                    }
                }
            }
        }
        for (Cell availableTile : viewData.getAvailableTiles()) {
            playField.add(new AvailableTileView(width, height,
                            new Cell(availableTile.getRow(), availableTile.getCol())).getImageView(),
                    availableTile.getRow(),
                    availableTile.getCol());
        }
    }

    public void updateFieldView(ViewData viewData) {
        removeAvailable();
        if (viewData.getStatusValue()) {
            setAvailable(viewData.getAvailableTiles());
        }
        this.viewData = viewData;
        setChips(viewData);
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
        playField.add(chip.getImageView(), row, col);
    }

    private void setAvailable(Vector<Cell> availableTiles) {
        for (Cell availableTile : availableTiles) {
            int row = availableTile.getRow();
            int col = availableTile.getCol();
            AvailableTileView availableTileView = new AvailableTileView(width, height, new Cell(row, col));
            playField.add(availableTileView.getImageView(), row, col);
        }
    }

    private void removeAvailable() {
        for (Cell availableTile : viewData.getAvailableTiles()) {
            int row = availableTile.getRow();
            int col = availableTile.getCol();
            ImageView imageViewInCell = (ImageView) playField.lookup("#" +
                    row + col);
            playField.getChildren().remove(imageViewInCell);
        }
    }

    private void setChips(ViewData viewData) {
        for (Cell changedColorTile : viewData.getChangeColorTiles()) {
            ImageView imageViewInCell = (ImageView) playField.lookup(
                    "#" + Integer.toString(changedColorTile.getRow())
                            + Integer.toString(changedColorTile.getCol()) + "chip"
            );
            playField.getChildren().remove(imageViewInCell);
            this.setChip(changedColorTile);
        }
    }

    public void deleteField() {
        playField.getChildren().clear();
    }


}
