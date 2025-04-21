package com.fuzis.compmathlab4.Messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzis.compmathlab4.Math.Approxes;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToGraph;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToSolve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    @Value("${rabbitmq.to_solve}")
    private String toSolveQueue;
    @Value("${rabbitmq.to_graph}")
    private String toGraphQueue;


    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private MessageService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    Logger log = LoggerFactory.getLogger(MessageService.class);

    public Optional<String> objToJson(Object obj) {
        try {
            String objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return Optional.of(objJackson);
        } catch (JsonProcessingException e) {
            log.error("failed conversion: Object to Json", e);
        }
        return Optional.empty();
    }

    public void send_to_solve(MessageToSolve message, Long chatId, Approxes approx) {
        var msg_json = objToJson(message);
        if (msg_json.isPresent()) {
            Message msg = MessageBuilder.withBody(msg_json.get().getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setHeader("ChatId", chatId)
                    .setHeader("Approx", approx.name())
                    .build();
            rabbitTemplate.send(toSolveQueue, msg);
        } else {
            log.warn("Message was not sent to solver: {}", message.toString());
        }
    }

    public void send_to_graph(MessageToGraph message, Long chatId, Approxes approx) {
        var msg_json = objToJson(message);
        if (msg_json.isPresent()) {
            Message msg = MessageBuilder.withBody(msg_json.get().getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setHeader("ChatId", chatId)
                    .setHeader("Approx", approx.name())
                    .build();
            rabbitTemplate.send(toGraphQueue, msg);
        } else {
            log.warn("Message was not sent to solver: {}", message.toString());
        }
    }

}
