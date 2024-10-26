package com.gradingSystem.services.CLI.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReaderThread extends Thread {
    private BufferedReader in;

    public ClientReaderThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Error reading server message: " + e.getMessage());
        }
    }
}
