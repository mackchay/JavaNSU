module com.example.reversi {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ru.nsu.ccfit.haskov.view to javafx.fxml;
    exports ru.nsu.ccfit.haskov.view;
    exports com.example.reversi;
    opens com.example.reversi to javafx.fxml;
}