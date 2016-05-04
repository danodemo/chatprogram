package tiy.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dominique on 4/27/2016.
 */
public class SampleServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Running simple server ...");

        // start a server on a specific port
        ServerSocket serverListener = new ServerSocket(8005);

        while (true) {
            try {
                Socket clientSocket = serverListener.accept();

                // display information about who just connected to our server
                System.out.println("Incoming connection from " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new ServerThread(clientSocket)).start();

            } catch (Exception exception) {
                System.out.println("Client closed connection! (how rude!)");
            }
        }
    }
}