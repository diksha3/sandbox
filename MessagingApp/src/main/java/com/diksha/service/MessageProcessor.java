package com.diksha.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class MessageProcessor {



    private final IMessageService messageService;

 /*   public MessageProcessor(IMessageService messageService) {
        this.messageService = messageService;
    }*/

    public String sendMessage(String message) {
        return messageService.sendMessage(message);
    }
    public String sendMessageWithId(int id) {
        return messageService.sendMessageWithId(" default message", id);
    }
}
