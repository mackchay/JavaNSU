module com.example.reversi {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ru.nsu.ccfit.haskov.view to javafx.fxml;
    exports ru.nsu.ccfit.haskov.view;
    exports ru.nsu.ccfit.haskov.reversi;
    opens ru.nsu.ccfit.haskov.reversi to javafx.fxml;
}