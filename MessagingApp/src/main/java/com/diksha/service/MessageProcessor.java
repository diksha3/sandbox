package com.diksha.service;


import com.diksha.service.IMessageService;

public class MessageProcessor {

    IMessageService messageService;

    public MessageProcessor(IMessageService messageService) {
        this.messageService = messageService;
    }

    public String sendMessage(String message) {
        return messageService.sendMessage(message);
    }
    public String sendMessageWithId(int id) {
        return messageService.sendMessageWithId(" default message", id);
    }
}
