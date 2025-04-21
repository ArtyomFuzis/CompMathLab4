package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.AnimeMode;
import com.fuzis.compmathlab4.DialogModes.CommunismMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SwitchHandler implements ResponseMethod
{
    @Autowired
    public SwitchHandler(ApplicationContext ctx){
        this.ctx = ctx;
    }

    public ApplicationContext ctx;

    @Override
    public void handle(ChatState state, Update update) {
        switch (callbackOnly(update)) {
            case CANCEL_SWITCH -> {
                state.setMeth(ctx.getBean(StartResponseHandler.class));
                state.getMode().getSwitchCancelled(state);
                state.getMode().getStartMessage(state);
            }
            case DO_SWITCH -> {
                if(state.getMode().getClass() == AnimeMode.class){
                    state.setMode(ctx.getBean(CommunismMode.class));
                } else {
                    state.setMode(ctx.getBean(AnimeMode.class));
                }
                state.getMode().getMakeSwitch(state);
                state.setMeth(ctx.getBean(StartResponseHandler.class));
                state.getMode().getStartMessage(state);
            }
            case null, default -> {
                if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().trim().equalsIgnoreCase("да")){
                    if(state.getMode().getClass() == AnimeMode.class){
                        state.setMode(ctx.getBean(CommunismMode.class));
                    } else {
                        state.setMode(ctx.getBean(AnimeMode.class));
                    }
                    state.getMode().getMakeSwitch(state);
                    state.setMeth(ctx.getBean(StartResponseHandler.class));
                    state.getMode().getStartMessage(state);
                }
                else state.getMode().getNotChoose(state);
            }

        };
    }
}
