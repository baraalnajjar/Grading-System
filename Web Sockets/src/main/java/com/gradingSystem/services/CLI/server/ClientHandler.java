package com.gradingSystem.services.CLI.server;

import com.gradingSystem.services.CLI.role.Role;

import java.io.*;
import java.net.*;

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String rolePrompt = Role.getPrompt();
            out.println("Choose one of the following roles:\n" + rolePrompt);

            String clientChoice = in.readLine();
            System.out.println("Received from client: " + clientChoice);

            Role chosenRole = null;
            try {
                int choiceIndex = Integer.parseInt(clientChoice);
                if (choiceIndex > 0 && choiceIndex <= Role.values().length) {
                    chosenRole = Role.values()[choiceIndex - 1];
                }
            } catch (NumberFormatException e) {
                out.println("Invalid choice. Please restart and choose a valid option.");
                in.close();
                out.close();
                clientSocket.close();
                return;
            }

            if (chosenRole != null) {
                out.println("Hello " + chosenRole.getDisplayName() + "!");
                chosenRole.getStrategy().handleRole(out,in);
            } else {
                out.println("Invalid choice. Please restart and choose a valid option.");
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
