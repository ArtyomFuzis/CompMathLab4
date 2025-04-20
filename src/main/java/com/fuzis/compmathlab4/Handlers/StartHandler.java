package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import com.fuzis.compmathlab4.Transfer.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    public List<SendMessage> handle(UserState state, Update update) {
        state.setMeth(ctx.getBean(StartResponseHandler.class));
        return Collections.singletonList(state.getMode().getStartMessage());
    }
}
