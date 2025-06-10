package com.diksha.service;

public class SmsService implements IMessageService {

    @Override
    public String sendMessage(String messageSender) {
        return "Sms received from " + messageSender;
    }

    @Override
    public String sendMessageWithId(String messageSender, int id) {
        return "Sms received from " + messageSender + id;
    }
}
