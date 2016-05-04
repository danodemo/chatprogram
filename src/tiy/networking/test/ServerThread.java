package tiy.networking.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServerThread implements Runnable {

    Socket clientSocket;
    SimpleServer chatServer;

    public ServerThread(Socket clientSocket, SimpleServer chatServer) {
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
    }







    public void run() {
        System.out.println("Running thrad");

        BufferedReader inputFromClient = null;
        PrintWriter outputToClient = null;

        try {
            // this is how we read from the client
            inputFromClient =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // this is how we write back to the client
            outputToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);

            // read from the input until the client disconnects
            String inputLine;
            while ((inputLine = inputFromClient.readLine()) != null) {
                System.out.println("Received message: " +
                        inputLine + " from " + clientSocket.toString());
                SimpleChatMessage chatMessage = new SimpleChatMessage();
                chatMessage.message = inputLine;
                chatMessage.timeCreated = LocalDateTime.now();
                chatMessage.sender = clientSocket.getInetAddress().getHostAddress();

                chatServer.addMessageToHistory(chatMessage);

                ArrayList<SimpleChatMessage> messageHistory = chatServer.getMessageHistory();
                for (SimpleChatMessage archivedMessage : messageHistory) {
                    outputToClient.println( "From " + archivedMessage.sender +
                            " @ " + archivedMessage.timeCreated.toString() +
                            " -> " + archivedMessage.message);
                }
                outputToClient.println("server-done");
            }

            inputFromClient.close();
            outputToClient.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (inputFromClient != null) {
                    inputFromClient.close();
                }
                if (outputToClient != null) {
                    outputToClient.close();
                }
            } catch (Exception closeException) {
                System.out.println("Error closing connections!");
            }

        }
    }
}