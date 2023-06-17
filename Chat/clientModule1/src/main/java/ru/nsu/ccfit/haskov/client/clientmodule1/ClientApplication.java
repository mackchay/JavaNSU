package ru.nsu.ccfit.haskov.client.clientmodule1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication {
    ClientApplication(Stage stage, RegisterData data) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("client-view.fxml"));
        Parent root = fxmlLoader.load();
        ClientController controller = fxmlLoader.getController();
        controller.setModel(data);
        controller.setUserName(data.getUserName());

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(controller::onCloseRequest);
        controller.connectToClient();
    }
}
