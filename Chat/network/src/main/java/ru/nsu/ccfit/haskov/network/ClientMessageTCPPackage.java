package ru.nsu.ccfit.haskov.network;

public class ClientMessageTCPPackage extends TCPPackage {
    private String userName = "";
    private final String message;

    public ClientMessageTCPPackage(String message) {
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
