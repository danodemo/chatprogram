package tiy.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Dominique on 4/27/2016.
 */
public class SampleClient {

    public static void main(String[] args) throws Exception {
        String dan = "172.168.4.15";
        String local = "localhost";
        // connect to the server on the target port
        Socket clientSocket = new Socket(local, 8005);

        // once we connect to the server, we also have an input and output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String userInput = inputScanner.nextLine();
            Message lastMessage = new Message(clientSocket.getInetAddress().toString(), userInput);
            out.println(lastMessage);

            String serverResponse;
            while (true) {
                serverResponse = in.readLine();
                if (serverResponse != null && serverResponse.equalsIgnoreCase("server-done")) {
                    break;
                }
                System.out.println("Waiting for server response ... ");
                System.out.println("Server::" + serverResponse);
                System.out.println("Got server respnose!");
            }
//            String serverResponse = in.readLine();
//            String doneMessage = in.readLine();
//            System.out.println("Done getting all the server responses");

            if (userInput.equalsIgnoreCase("exit")) {
                clientSocket.close();
                break;
            }
        }

        // close the connection

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
