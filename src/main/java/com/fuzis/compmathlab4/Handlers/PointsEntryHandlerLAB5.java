package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class PointsEntryHandlerLAB5 implements ResponseMethod
{
    @Autowired
    public PointsEntryHandlerLAB5(ApplicationContext ctx, Calculator calc) {
        this.ctx = ctx;
        this.calc = calc;
    }
    ApplicationContext ctx;
    Calculator calc;

    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())  {state.getMode().getNotChoose(state);state.getMode().getDecreaseSocialCredits(state, update);return;}
        validatePointsAndSendLAB5(update.getMessage().getText(), state, calc, ctx, update);
    }
}
