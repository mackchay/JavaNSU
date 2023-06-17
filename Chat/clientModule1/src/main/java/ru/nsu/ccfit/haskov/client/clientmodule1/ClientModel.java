package ru.nsu.ccfit.haskov.client.clientmodule1;

import ru.nsu.ccfit.haskov.network.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientModel {
    private ObjectOutputStream out;

    private Socket socket;

    private TCPPackageListener tcpPackageListener;

    private RegisterData registerData;

    private static final int TIMEOUT = 100000;

    public void setData(RegisterData data) {
        registerData = data;
    }
    public void connect() throws IOException {
        try (
                Socket socket = new Socket(registerData.getIpAddr(), registerData.getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            this.socket = socket;
            this.out = out;
            socket.setSoLinger(true, TIMEOUT);
            sendUserName(registerData.getUserName());
            Thread thread = new Thread(this::listen);
            thread.start();
            while (true) {

            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            socket.close();
        }
    }

    private void listen() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (!Thread.currentThread().isInterrupted()) {
                TCPPackage tcpPackage = (TCPPackage) in.readObject();
                notifyTcpPackageListener(tcpPackage.getServerMessage());
            }
        } catch (IOException e) {
            System.out.println("Disconnected.");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong tcp package.");
        }
    }
    private void sendUserName(String userName) throws IOException {
        out.writeObject(new ClientConnectedTCPPackage(userName));
        out.flush();
    }

    public void getMembersList() throws IOException {
        out.writeObject(new ClientListTCPPackage());
        out.flush();
    }

    public void sendMessage(String message) throws IOException {
        out.writeObject(new ClientMessageTCPPackage(message));
        out.flush();
    }

    public void sendDisconnect() throws IOException {
        out.writeObject(new ClientDisconnectedTCPPackage());
    }
    public void setTcpPackageListener(TCPPackageListener tcpPackageListener) {
        this.tcpPackageListener = tcpPackageListener;
    }

    public void notifyTcpPackageListener(String message) {
        tcpPackageListener.onPackageReceived(message);
    }
}
