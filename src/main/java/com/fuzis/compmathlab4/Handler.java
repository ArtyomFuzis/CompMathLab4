package com.fuzis.compmathlab4;

import com.fuzis.compmathlab4.Transfer.UserState;
import com.fuzis.compmathlab4.DialogModes.CommunismMode;
import com.fuzis.compmathlab4.Handlers.StartHandler;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Service
public class Handler
{
    @Autowired
    public Handler(StartHandler baseHandler, CommunismMode baseMode){
        this.baseHandler = baseHandler;
        this.baseMode = baseMode;
    }

    DialogMode baseMode;
    ResponseMethod baseHandler;

    HashMap<Long, UserState> userStates = new HashMap<>();
    public List<SendMessage> handleMessage(Update update){
        Long chatId = update.hasMessage() ? update.getMessage().getChatId() : update.getCallbackQuery().getMessage().getChatId();
        if(!userStates.containsKey(chatId) || (update.hasMessage()&&update.getMessage().getText()!=null&&update.getMessage().getText().trim().equals("/start"))){
            userStates.put(chatId, new UserState(baseMode, baseHandler, new Stack<>()));
        }
        UserState state = userStates.get(chatId);
        List<SendMessage> msgs =  state.getMeth().handle(state, update);
        for(var msg: msgs) {
            msg.setChatId(chatId);
            msg.setParseMode(ParseMode.HTML);
        }
        return msgs;
    }
}
