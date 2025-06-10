package com.diksha.service;

public class EmailService implements IMessageService{
    @Override
    public String sendMessage(String messageSender) {
        return "Email received from" + messageSender;
    }

    @Override
    public String sendMessageWithId(String messageSender, int id) {
        return "Email received from" + messageSender + id;
    }
}
