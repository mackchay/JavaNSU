package com.example.reversi;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.nsu.ccfit.haskov.view.BlackChip;
import ru.nsu.ccfit.haskov.view.Chip;
import ru.nsu.ccfit.haskov.view.ReversiController;
import ru.nsu.ccfit.haskov.view.WhiteChip;

import java.util.Objects;
import java.util.Vector;

public class FieldView {
    private final GridPane play_field;
    ReversiController controller;
    private final static int layoutX = 320;
    private final static int layoutY = 35;
    private final static int field_size = 8;
    private final static int height = 80;
    private final static int width = 80;

    public FieldView(GridPane gridPane, ReversiController reversiController) {
        controller = reversiController;
        play_field = gridPane;
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
                }
                else {
                    button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                StackPane stackPane = new StackPane(button);
                play_field.add(stackPane, i, j);
                if ((i == field_size /2 || i == field_size /2 - 1) && (j == field_size /2 || j == field_size /2 - 1)) {
                    if (i == j) {
                        play_field.add(new BlackChip(width, height).getImageView(), i, j);
                    }
                    else {
                        play_field.add(new WhiteChip(width, height).getImageView(), i, j);
                    }
                }
            }
        }
    }

    public Pane getPane() {
        return play_field;
    }

    public void updateFieldView(Vector<Integer[]> changeColorVector,
                                int color) {
        for (Integer[] changeColorVectorElem: changeColorVector) {
            ImageView imageViewInCell = null;
            for (Node node : play_field.getChildren()) {
                Integer row = changeColorVectorElem[0];
                Integer col = changeColorVectorElem[1];
                if (Objects.equals(GridPane.getRowIndex(node), row) &&
                        Objects.equals(GridPane.getColumnIndex(node), col)) {
                    imageViewInCell = (ImageView) node.lookup("ChipImage");
                    play_field.getChildren().remove(imageViewInCell);
                    this.setChip(color, row, col);
                    break;
                }
            }
        }
    }

    private void setChip(int color, int row, int col) {
        Chip chip;
        if (color == 1) {
            chip = new BlackChip(width, height);
        }
        else {
            chip = new WhiteChip(width, height);
        }
        play_field.add(chip.getImageView(), row, col);
    }
}