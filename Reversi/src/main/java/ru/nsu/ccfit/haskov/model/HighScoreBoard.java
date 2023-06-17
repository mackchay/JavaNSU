package ru.nsu.ccfit.haskov.model;

import javafx.scene.text.Text;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class HighScoreBoard {

    HighScoreBoard() {

    }

    void addRecord(int playerScore, int opponentScore) {
        try {
            String filename = "src/main/files/high-scores.txt";
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int index = 0;
            Vector<String[]> vector = new Vector<>();
            while ((line = bufferedReader.readLine()) != null && index < 5) {
                String[] data = line.split(" ");
                if (playerScore - opponentScore > (Integer.parseInt(data[0]) - Integer.parseInt(data[1]))) {
                    vector.add(new String[]{Integer.toString(playerScore), Integer.toString(opponentScore)});
                    vector.add(data);
                    index += 2;
                }
                else {
                    vector.add(data);
                    index += 1;
                }
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter(filename);
            for (String[] data: vector) {
                fileWriter.write(data[0] + " " + data[1] + "\n");
            }
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("ErrorOutput");
        }
    }
}
