package ru.nsu.ccfit.haskov.client.clientmodule1;

public class RegisterData {
    private final String ipAddr;
    private final int port;
    private final String userName;

    RegisterData(String ipAddr, int port, String userName) {
        this.ipAddr = ipAddr;
        this.port = port;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPort() {
        return port;
    }

    public String getIpAddr() {
        return ipAddr;
    }
}
