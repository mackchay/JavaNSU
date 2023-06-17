package ru.nsu.ccfit.haskov.client.clientmodule1;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ClientController {
    private ClientModel model = new ClientModel();

    @FXML
    private Stage stage;
    private Thread infiniteThread;
    @FXML
    private Text fieldUserName;
    @FXML
    private TextArea fieldLog;
    @FXML
    private TextField fieldMessage;

    @FXML
    private void initialize() {
        fieldLog.setEditable(false);
        observedModelChanges();
        fieldMessage.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                onSendMessage();
            }
        });
    }

    public void setUserName(String userName) {
        fieldUserName.setText(userName);
    }
    public void setModel(RegisterData data) {
        model.setData(data);
    }

    public void connectToClient() {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                model.connect();
                return null;
            }
        };

        infiniteThread = new Thread(task);
        infiniteThread.setDaemon(true);
        infiniteThread.start();
    }
    private void onSendMessage() {
        if (fieldMessage.getText().equals("")) {
            return;
        }
        try {
            model.sendMessage(fieldMessage.getText());
        } catch (IOException e) {
            printMessage("Sending message error.");
        }
        fieldMessage.setText("");
    }

    public void getMembersList() {
        try {
            model.getMembersList();
        } catch (IOException e) {
            printMessage("Can't get messages.");
        }
    }
    private synchronized void printMessage(String message) {
        Platform.runLater(() -> fieldLog.appendText(message + "\n"));
    }

    public void stopInfiniteFunction() {
        if (infiniteThread != null && infiniteThread.isAlive()) {
            infiniteThread.interrupt();
        }
    }
    private void observedModelChanges() {
        model.setTcpPackageListener(this::printMessage);
    }

    private void onDisconnect() {
        try {
            model.sendDisconnect();
        } catch (IOException e) {
            printMessage("You are disconnected.");
        }
    }
    public void onCloseRequest(WindowEvent event) {
        onDisconnect();
        stopInfiniteFunction();
    }
}
