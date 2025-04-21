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
public class StartCalcHandler implements ResponseMethod {

    @Autowired
    public StartCalcHandler(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    ApplicationContext ctx;

    @Override
    public void handle(ChatState state, Update update) {
        switch (callbackOnly(update)) {
            case MANUALLY_WRITE -> {
                state.setMeth(ctx.getBean(PointsEntryHandler.class));
                state.getMode().getPointsEntry(state);
            }
            case SEND_FILE -> {
                state.setMeth(ctx.getBean(PointsEntryFileHandler.class));
                state.getMode().getPointsEntryFile(state);
            }
            case null, default ->  state.getMode().getNotChoose(state);
        };
    }
}
