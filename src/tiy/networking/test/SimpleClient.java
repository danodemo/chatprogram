package tiy.networking.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) throws Exception {
        // connect to the server on the target port
        Socket clientSocket = new Socket("localhost", 8005);

        // once we connect to the server, we also have an input and output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String userInput = inputScanner.nextLine();
            out.println(userInput);


            String serverResponse; //This is where the client listens for the server's response
            while (true) {
                serverResponse = in.readLine();
                if (serverResponse != null && serverResponse.equalsIgnoreCase("server-done")) {
                    break;
                }
                System.out.println("Server::" + serverResponse);

            }
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
        }

        // close the connection
        clientSocket.close();
    }

    public static void sendMessage(String message) {
        try {
            String dan = "172.168.4.15";
            String local = "localhost";
            // connect to the server on the target port
            Socket clientSocket = new Socket(local, 8005);

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(message);

            // close the connection
            clientSocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }








}
