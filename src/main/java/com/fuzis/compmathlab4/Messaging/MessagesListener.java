package com.fuzis.compmathlab4.Messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagesListener {

    @Autowired
    private MessagesListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    private final ObjectMapper objectMapper;


    @RabbitListener(queues = "${rabbitmq.to_response}")
    public void receiveMessageFromHandler(String message) throws JsonProcessingException {
        MessageResponse msg = objectMapper.readValue(message, MessageResponse.class);
        System.out.println(msg);
    }
}
