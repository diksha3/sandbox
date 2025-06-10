package org.example.controller;

import org.example.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Autowired
    MessageProcessor messageProcessor ;

    @GetMapping("/sendMessage")
    public void sendMessageEndpoint(@RequestParam String inputString){
        messageProcessor.sendMessage(inputString);
    }
}
