package tiy.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Dominique on 4/27/2016.
 */
public class ServerThread implements Runnable {

    Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        System.out.println("Running " + Thread.currentThread().getId());

        try {
            // this is how we read from the client
            BufferedReader inputFromClient =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // this is how we write back to the client
            PrintWriter outputToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);

            // read from the input until the client disconnects
            String inputLine;
            while ((inputLine = inputFromClient.readLine()) != null) {
                System.out.println(inputLine);
                outputToClient.println("Message received loud and clear");
                outputToClient.println("server-done");
            }

            inputFromClient.close();
            outputToClient.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Done running " + Thread.currentThread().getId());
    }
}