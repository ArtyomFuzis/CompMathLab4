package com.fuzis.compmathlab4;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.CommunismMode;
import com.fuzis.compmathlab4.Handlers.StartHandler;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

@Service
public class Handler
{
    @Autowired
    public Handler(ApplicationContext ctx){
        this.ctx = ctx;
    }

    ApplicationContext ctx;

    HashMap<Long, ChatState> userStates = new HashMap<>();
    public void handleMessage(Update update){
        Long chatId = update.hasMessage() ? update.getMessage().getChatId() : update.getCallbackQuery().getMessage().getChatId();
        if(!userStates.containsKey(chatId) || (update.hasMessage()&&update.getMessage().hasText()&&update.getMessage().getText().trim().equals("/start"))){
            userStates.put(chatId, new ChatState(ctx.getBean(CommunismMode.class), ctx.getBean(StartHandler.class), chatId));
        }
        ChatState state = userStates.get(chatId);
        state.getMeth().handle(state, update);
    }
}
