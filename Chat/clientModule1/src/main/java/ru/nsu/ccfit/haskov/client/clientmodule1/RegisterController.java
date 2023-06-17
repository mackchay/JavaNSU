package ru.nsu.ccfit.haskov.client.clientmodule1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Button registerButton;
    @FXML
    private Text exceptionMessage;
    @FXML
    private TextField userName;
    @FXML
    private TextField port;
    @FXML
    private TextField ipAddr;

    @FXML
    protected void onRegisterButtonClick() {
        try {
            ClientApplication application = new ClientApplication(
                    new Stage(),
                    new RegisterData(
                            ipAddr.getText(),
                            Integer.parseInt(port.getText()),
                            userName.getText()
                    )
            );
            exceptionMessage.setText("");
            Stage stage = (Stage) exceptionMessage.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            exceptionMessage.setText("Connection error.");
        } catch (NumberFormatException e) {
            exceptionMessage.setText("Incorrect port format.");
        }
    }
}