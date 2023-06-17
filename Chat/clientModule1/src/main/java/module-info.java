module ru.nsu.ccfit.haskov.client.clientmodule1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires network;
    requires java.desktop;


    opens ru.nsu.ccfit.haskov.client.clientmodule1 to javafx.fxml;
    exports ru.nsu.ccfit.haskov.client.clientmodule1;
}