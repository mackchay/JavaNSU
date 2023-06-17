package ru.nsu.ccfit.haskov.network;

public class ClientConnectedTCPPackage extends TCPPackage {
    private final String userName;
    public ClientConnectedTCPPackage(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
