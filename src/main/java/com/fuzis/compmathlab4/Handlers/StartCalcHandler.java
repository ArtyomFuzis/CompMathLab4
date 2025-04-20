package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Transfer.UserState;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
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
    public List<SendMessage> handle(UserState state, Update update) {
        return switch (callbackOnly(update)) {
            case MANUALLY_WRITE -> {
                state.setMeth(ctx.getBean(PointsEntryHandler.class));
                yield Collections.singletonList(state.getMode().getPointsEntry());
            }
            case null, default ->  Collections.singletonList(state.getMode().getNotChoose());
        };
    }
}
