package ru.nsu.ccfit.haskov.view;

import javafx.scene.text.Text;
import ru.nsu.ccfit.haskov.model.CellColor;

public class TurnView {
    Text blackTurn;
    Text whiteTurn;

    TurnView(Text blackTurn, Text whiteTurn) {
        this.blackTurn = blackTurn;
        this.whiteTurn = whiteTurn;
    }

    public void setTurnView(CellColor cellColor) {
        if (cellColor.equals(CellColor.BLACK)) {
            whiteTurn.setText("TAKES TURN");
            blackTurn.setText("");
        }
        else {
            whiteTurn.setText("");
            blackTurn.setText("TAKES TURN");
        }
    }
}
