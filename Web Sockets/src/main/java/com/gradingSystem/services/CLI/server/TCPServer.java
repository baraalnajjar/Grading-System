package com.gradingSystem.services.CLI.server;

import com.gradingSystem.services.CLI.server.ClientHandler;

import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new Client Connected");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
