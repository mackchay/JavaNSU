module ru.nsu.ccfit.haskov {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ru.nsu.ccfit.haskov.view to javafx.fxml;
    opens ru.nsu.ccfit.haskov.highscore to javafx.fxml;
    exports ru.nsu.ccfit.haskov.view;
    exports ru.nsu.ccfit.haskov.reversi;
    exports ru.nsu.ccfit.haskov.highscore;
    opens ru.nsu.ccfit.haskov.reversi to javafx.fxml;
}