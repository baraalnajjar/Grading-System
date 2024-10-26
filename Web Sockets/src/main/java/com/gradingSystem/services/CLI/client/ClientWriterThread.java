package com.gradingSystem.services.CLI.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientWriterThread extends Thread {
    private PrintWriter out;
    private BufferedReader userInput;

    public ClientWriterThread(PrintWriter out, BufferedReader userInput) {
        this.out = out;
        this.userInput = userInput;
    }

    @Override
    public void run() {
        try {
            String userResponse;
            while (true) {

                userResponse = userInput.readLine();  // Read user input
                if (userResponse.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;  // Exit if user types 'exit'
                }
                out.println(userResponse);  // Send user input to server
            }
        } catch (IOException e) {
            System.out.println("Error sending message to server: " + e.getMessage());
        }
    }
}
