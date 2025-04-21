package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Math.CalcConductor;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Component
public class PointsEntryHandler implements ResponseMethod
{
    @Autowired
    public PointsEntryHandler(ApplicationContext ctx, CalcConductor calcConductor) {
        this.ctx = ctx;
        this.calcConductor = calcConductor;
    }
    ApplicationContext ctx;
    CalcConductor calcConductor;

    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())  {state.getMode().getNotChoose(state);return;}
        validatePoints(update.getMessage().getText(), state, calcConductor, ctx);
    }
}
