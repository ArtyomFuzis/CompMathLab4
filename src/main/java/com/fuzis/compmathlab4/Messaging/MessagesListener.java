package com.fuzis.compmathlab4.Messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzis.compmathlab4.Math.Approxes;
import com.fuzis.compmathlab4.Math.CalcConductor;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageFromGraph;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MessagesListener {

    @Autowired
    private MessagesListener(ObjectMapper objectMapper, CalcConductor calcConductor) {
        this.objectMapper = objectMapper;
        this.calcConductor = calcConductor;
    }
    CalcConductor calcConductor;

    private final ObjectMapper objectMapper;


    @RabbitListener(queues = "${rabbitmq.to_response}")
    public void receiveMessageFromCalc(String message, @Header("ChatId") Long chatId, @Header("Approx") String approx) throws JsonProcessingException {
        MessageResponse msg = objectMapper.readValue(message, MessageResponse.class);
        calcConductor.continueCalculations(msg.X(), chatId, Approxes.valueOf(approx));
    }
    @RabbitListener(queues = "${rabbitmq.from_graph}")
    public void receiveMessageFromGraph(String message, @Header("ChatId") Long chatId, @Header("Approx") String approx) throws JsonProcessingException {
        MessageFromGraph msg = objectMapper.readValue(message, MessageFromGraph.class);
        System.out.println("SUS: " + msg.graph());
    }
}
