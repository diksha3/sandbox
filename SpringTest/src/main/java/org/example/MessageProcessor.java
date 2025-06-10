package org.example;

import org.example.model.MessageService;

public class MessageProcessor {

    MessageService messageService ;
    public MessageProcessor(MessageService messageService){
        this.messageService = messageService ;
    }

    public void sendMessage(String message){
        messageService.sendMessage(message);
    }
}
