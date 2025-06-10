package com.diksha.config;


import com.diksha.service.EmailService;
import com.diksha.service.IMessageService;
import com.diksha.service.MessageProcessor;
import com.diksha.service.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    @Primary
    public IMessageService getSmsService() {
        return new SmsService();
    }
    @Bean
    public IMessageService getEmailService() {
        return new EmailService();
    }

    @Bean
    public MessageProcessor messageProcessor(IMessageService messageService) {
        return new MessageProcessor(messageService);
    }

    @Bean
    public MessageProcessor messageProcessor2(IMessageService messageService) {
        return new MessageProcessor(messageService);
    }


}
