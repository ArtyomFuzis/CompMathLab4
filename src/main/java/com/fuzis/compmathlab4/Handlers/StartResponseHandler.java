package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

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
            case START_CALC_LAB4 -> {
                state.setMeth(ctx.getBean(StartCalcHandlerLAB4.class));
                state.getMode().getStartCalculationsLAB4(state);
            }
            case SWITCH_MODE -> {
                state.setMeth(ctx.getBean(SwitchHandler.class));
                state.getMode().getSwitchMode(state);
            }
            case START_CALC_LAB5 -> {
                state.setMeth(ctx.getBean(StartCalcHandlerLAB5.class));
                state.getMode().getStartCalculationsLAB5(state);
            }
            case null, default -> {
                state.getMode().getDecreaseSocialCredits(state, update);
                state.getMode().getNotChoose(state);
            }
        };
    }
}
