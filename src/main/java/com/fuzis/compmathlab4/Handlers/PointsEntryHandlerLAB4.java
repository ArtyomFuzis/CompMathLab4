package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB4.CalcConductor;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class PointsEntryHandlerLAB4 implements ResponseMethod
{
    @Autowired
    public PointsEntryHandlerLAB4(ApplicationContext ctx, CalcConductor calcConductor) {
        this.ctx = ctx;
        this.calcConductor = calcConductor;
    }
    ApplicationContext ctx;
    CalcConductor calcConductor;

    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())  {state.getMode().getNotChoose(state);state.getMode().getDecreaseSocialCredits(state, update);return;}
        validatePointsAndSendLAB4(update.getMessage().getText(), state, calcConductor, ctx, update);
    }
}
