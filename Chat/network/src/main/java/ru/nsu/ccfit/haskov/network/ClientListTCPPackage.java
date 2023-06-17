package ru.nsu.ccfit.haskov.network;

import java.util.ArrayList;

public class ClientListTCPPackage extends TCPPackage {

    private ArrayList<String> clientList;

    public ClientListTCPPackage() {
        super();
    }
    public ArrayList<String> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<String> clientList) {
        this.clientList = clientList;
    }
}
