package ru.nsu.ccfit.haskov.client.clientmodule1;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClientModel clientModel = new ClientModel();
        clientModel.setData(new RegisterData("127.0.0.1", 12345, "egor"));
        while (true) {

        }
    }
}
