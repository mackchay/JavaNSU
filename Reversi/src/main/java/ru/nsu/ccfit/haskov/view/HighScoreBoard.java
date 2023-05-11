package ru.nsu.ccfit.haskov.view;

import javafx.scene.text.Text;

import java.io.*;
import java.util.Scanner;

public class HighScoreBoard extends ScoreBoard {
    private final String filename = "highscore.txt";
    HighScoreBoard(Text text) {
        super(text);
        Scanner scanner = new Scanner(filename);
        textField.setText(scanner.nextLine());
    }

    void addRecord(int value) {
        this.setTextField(value);
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(value);
        }
        catch (IOException e) {
            System.out.println("ErrorOutput");
        }
    }
}
