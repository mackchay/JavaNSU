package com.example.reversi;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.nsu.ccfit.haskov.view.BlackChip;
import ru.nsu.ccfit.haskov.view.ReversiController;
import ru.nsu.ccfit.haskov.view.WhiteChip;

public class Field {
    private final GridPane play_field;

    private final static int layoutX = 320;
    private final static int layoutY = 35;
    private final static int field_size = 8;
    private final static int height = 80;
    private final static int width = 80;

    public Field() {
        ReversiController reversiController = new ReversiController();
        play_field = new GridPane();
        play_field.setLayoutX(layoutX);
        play_field.setLayoutY(layoutY);
        play_field.prefHeight(field_size * height);
        play_field.prefWidth(field_size * width);
        play_field.setId("play_field");
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
                button.setOnAction(event -> reversiController.putChip(finalI, finalJ));
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

    public void setWhiteChip(int row, int col) {
        WhiteChip whiteChip = new WhiteChip(width, height);
        play_field.add(whiteChip.getImageView(), row, col);
    }

   public void setBlackChip(int row, int col) {
        BlackChip blackChip = new BlackChip(width, height);
        play_field.add(blackChip.getImageView(), row, col);
    }
}
