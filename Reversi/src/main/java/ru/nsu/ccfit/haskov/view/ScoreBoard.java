package ru.nsu.ccfit.haskov.view;

import javafx.scene.text.Text;

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
