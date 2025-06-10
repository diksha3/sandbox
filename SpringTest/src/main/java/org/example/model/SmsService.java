package org.example.model;

public class SmsService implements MessageService{
    @Override
    public void sendMessage(String message) {
        System.out.println("sending sms - hello diksha");
    }
}
