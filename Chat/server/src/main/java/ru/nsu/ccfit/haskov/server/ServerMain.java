package ru.nsu.ccfit.haskov.server;

import ru.nsu.ccfit.haskov.network.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerMain {
    private final static int PORT = 12345;
    private final static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private final static ExecutorService executorService = Executors.newFixedThreadPool(15);

    ServerMain() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            ArrayList<String> messageHistory = new ArrayList<>();
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(clientSocket, clientHandlers, messageHistory);
                    clientHandlers.add(handler);
                    executorService.execute(handler);
                } catch (IOException e) {
                    System.out.println("TCPConnection error!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            executorService.shutdown();
        }
    }
}