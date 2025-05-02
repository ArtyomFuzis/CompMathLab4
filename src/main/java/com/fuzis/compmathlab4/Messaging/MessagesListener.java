package com.fuzis.compmathlab4.Messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuzis.compmathlab4.MathLAB4.Approxes;
import com.fuzis.compmathlab4.MathLAB4.CalcConductor;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageFromGraph;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageResponse;
import com.fuzis.compmathlab4.Utils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class MessagesListener {

    @Autowired
    private MessagesListener(ObjectMapper objectMapper, CalcConductor calcConductor, Utils utils) {
        this.objectMapper = objectMapper;
        this.calcConductor = calcConductor;
        this.utils = utils;
    }
    CalcConductor calcConductor;
    Utils utils;
    private final ObjectMapper objectMapper;


    @RabbitListener(queues = "${rabbitmq.to_response}")
    public void receiveMessageFromCalc(String message, @Header("ChatId") Long chatId, @Header("Approx") String approx) throws JsonProcessingException {
        MessageResponse msg = objectMapper.readValue(message, MessageResponse.class);
        calcConductor.continueCalculations(msg.X(), chatId, Approxes.valueOf(approx));
    }
    @RabbitListener(queues = "${rabbitmq.from_graph}")
    public void receiveMessageFromGraph(String message, @Header("ChatId") Long chatId, @Header("Approx") String approx) throws IOException, NoSuchAlgorithmException {
        MessageFromGraph msg = objectMapper.readValue(message, MessageFromGraph.class);
        String uuid = utils.saveGraph(msg.graph());
        calcConductor.loadGraph(uuid, Approxes.valueOf(approx), chatId);
    }
}
