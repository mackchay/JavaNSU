package com.example.reversi;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ScoreBoard {
    Text textField;
    ScoreBoard(Text text) {
        this.textField = text;
        text.setText(Integer.toString(2));
    }

    void setTextField(int value) {
        textField.setText(Integer.toString(value));
    }
 }
