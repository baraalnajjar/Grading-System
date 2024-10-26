package com.gradingSystem.services.CLI.client;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // For user input

            ClientReaderThread readerThread = new ClientReaderThread(in);
            ClientWriterThread writerThread = new ClientWriterThread(out, userInput);

            readerThread.start();
            writerThread.start();

            readerThread.join();
            writerThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
