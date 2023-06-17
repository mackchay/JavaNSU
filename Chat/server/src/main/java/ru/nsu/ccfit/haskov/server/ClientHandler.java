package ru.nsu.ccfit.haskov.server;

import ru.nsu.ccfit.haskov.network.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    private static final int TIMEOUT = 100000;

    private final ArrayList<ClientHandler> clientHandlers;

    private final ArrayList<String> messageHistory;
    private final ObjectOutputStream out;

    private final ObjectInputStream in;

    private String userName = "unnamed";

    ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clientHandlers,
                  ArrayList<String> messageHistory) throws IOException {
        this.clientSocket = clientSocket;
        this.clientHandlers = clientHandlers;
        this.clientSocket.setSoLinger(true, TIMEOUT);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        this.messageHistory = messageHistory;
    }

    @Override
    public void run() {
        try {
            TCPPackage tcpPackage = (TCPPackage) in.readObject();
            userName = ((ClientConnectedTCPPackage) tcpPackage).getUserName();
            tcpPackage.setServerMessage(userName + " joined the chat.");
            StringBuilder serverAnswer = new StringBuilder();
            for (String message: messageHistory) {
                serverAnswer.append(message).append("\n");
            }
            TCPPackage response = new TCPPackage();
            response.setServerMessage(serverAnswer.toString());
            sendTCPPackage(response);
            broadcast(tcpPackage);
            while (!Thread.currentThread().isInterrupted()) {
                tcpPackage = (TCPPackage) in.readObject();
                if (tcpPackage instanceof ClientListTCPPackage) {
                    StringBuilder userNames = new StringBuilder();
                    userNames.append("List of members:\n");
                    synchronized (clientHandlers) {
                        for (ClientHandler handler : clientHandlers) {
                            userNames.append(handler.getUserName()).append("\n");
                        }
                    }
                    tcpPackage.setServerMessage(userNames.toString());
                    sendTCPPackage(tcpPackage);
                }
                if (tcpPackage instanceof ClientMessageTCPPackage) {
                    tcpPackage.setServerMessage(userName + ": " + ((ClientMessageTCPPackage) tcpPackage).getMessage());
                    broadcast(tcpPackage);
                }
                if (tcpPackage instanceof ClientDisconnectedTCPPackage) {
                    disconnect();
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTCPPackage(TCPPackage tcpPackage) {
        try {
            out.writeObject(tcpPackage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server can't send package.");
        }
    }

    public void broadcast(TCPPackage tcpPackage) {
        messageHistory.add(tcpPackage.getServerMessage());
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                handler.sendTCPPackage(tcpPackage);
            }
        }
    }

    private void disconnect() throws IOException {
        clientSocket.close();
        synchronized (clientHandlers) {
            clientHandlers.remove(this);
        }
        TCPPackage tcpPackage = new ClientDisconnectedTCPPackage();
        tcpPackage.setServerMessage(userName + " left the chat.");
        broadcast(tcpPackage);
        Thread.currentThread().interrupt();
    }

    public String getUserName() {
        return userName;
    }
}
