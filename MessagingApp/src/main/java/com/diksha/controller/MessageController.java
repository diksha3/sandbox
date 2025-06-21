package com.diksha.controller;

import com.diksha.service.MessageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {


    //@Qualifier("messageProcessor")
    private final MessageProcessor messageProcessorX ;
    @GetMapping("/getMessage")
    public String getMessage(@RequestParam(name = "message", defaultValue = "diksha") String message) {

        return messageProcessorX.sendMessage(message);
    }

    @GetMapping("/getMessage/{id}")
    public String getMessageWithId(@PathVariable("id") int id) {
        return messageProcessorX.sendMessageWithId(id);
    }

}
