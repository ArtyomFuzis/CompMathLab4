package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import com.fuzis.compmathlab4.Data.ChatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Component
public class StartHandler implements ResponseMethod {

    @Autowired
    public StartHandler(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    ApplicationContext ctx;

    @Override
    public void handle(ChatState state, Update update) {
        state.setMeth(ctx.getBean(StartResponseHandler.class));
        state.getMode().getStartMessage(state);
    }
}
