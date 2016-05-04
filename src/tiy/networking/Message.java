package tiy.networking;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    public String userName;
    public String userMessage;
//    public String timeStamp;

    public Message (String clientName, String clientMessage){
        userName = clientName;
        userMessage = clientMessage;

    }

    @Override
    public String toString() {
        return (userName + ": " + userMessage);
    }

}