package org.example.config;

import org.example.MessageProcessor;
import org.example.model.EmailService;
import org.example.model.MessageService;
import org.example.model.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    @Primary
    MessageService emailService(){
        return new EmailService() ;
    }

    @Bean
    MessageService smsService(){
        return new SmsService() ;
    }

    @Bean
    MessageProcessor messageProcessor(MessageService messageService){
        return new MessageProcessor(messageService) ;
    }


}
