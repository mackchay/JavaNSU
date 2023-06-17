package ru.nsu.ccfit.haskov.network;

import java.io.Serializable;

public class TCPPackage implements Serializable {
    private String message;

    public void setServerMessage(String message) {
        this.message = message;
    }
    public String getServerMessage() {
        return message;
    }
}
