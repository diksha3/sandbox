package com.diksha.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface IMessageService {

    String sendMessage(String messageSender);

    String sendMessageWithId(String messageSender, int id);
}
