package tiy.networking.test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleServer {

    public ArrayList<SimpleChatMessage> messageHistory = new ArrayList<SimpleChatMessage>();

    public static void main(String[] args) throws Exception {
        new SimpleServer().startServer();
    }

    public void startServer() throws Exception {
        System.out.println("Running simple server ...");

        // start a server on a specific port
        ServerSocket serverListener = new ServerSocket(8005);

        while (true) {
            try {
                Socket clientSocket = serverListener.accept();

                // display information about who just connected to our server
                System.out.println("Incoming connection from " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new ServerThread(clientSocket, this)).start();

            } catch (Exception exception) {
                System.out.println("Client closed connection! (how rude!)");
            }
        }
    }

    public synchronized void addMessageToHistory(SimpleChatMessage chatMessage) {
        messageHistory.add(chatMessage);
    }

    public synchronized ArrayList<SimpleChatMessage> getMessageHistory() {
        return messageHistory;
    }
}
