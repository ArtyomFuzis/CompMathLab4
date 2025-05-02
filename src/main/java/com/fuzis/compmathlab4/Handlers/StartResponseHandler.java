package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Component
public class StartResponseHandler implements ResponseMethod
{
    @Autowired
    public StartResponseHandler(ApplicationContext ctx){
        this.ctx = ctx;
    }

    public ApplicationContext ctx;

    @Override
    public void handle(ChatState state, Update update) {
        switch (callbackOnly(update)) {
            case START_CALC -> {
                state.setMeth(ctx.getBean(StartCalcHandler.class));
                state.getMode().getStartCalculations(state);
            }
            case SWITCH_MODE -> {
                state.setMeth(ctx.getBean(SwitchHandler.class));
                state.getMode().getSwitchMode(state);
            }
            case null, default -> {
                state.getMode().getDecreaseSocialCredits(state, update);
                state.getMode().getNotChoose(state);
            }
        };
    }
}
