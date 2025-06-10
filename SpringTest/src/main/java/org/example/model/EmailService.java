package org.example.model;

public class EmailService implements MessageService{
    @Override
    public void sendMessage(String message) {
        System.out.println("sending email - Hello "+message);
    }
}
