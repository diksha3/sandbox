package com.diksha.service;

public interface IMessageService {

    String sendMessage(String messageSender);

    String sendMessageWithId(String messageSender, int id);
}
