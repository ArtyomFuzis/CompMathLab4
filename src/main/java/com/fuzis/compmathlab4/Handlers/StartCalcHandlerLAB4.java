package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCalcHandlerLAB4 implements ResponseMethod {

    @Autowired
    public StartCalcHandlerLAB4(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    ApplicationContext ctx;

    @Override
    public void handle(ChatState state, Update update) {
        switch (callbackOnly(update)) {
            case MANUALLY_WRITE_LAB4 -> {
                state.setMeth(ctx.getBean(PointsEntryHandlerLAB4.class));
                state.getMode().getPointsEntry(state);
            }
            case SEND_FILE_LAB4 -> {
                state.setMeth(ctx.getBean(PointsEntryFileHandlerLAB4.class));
                state.getMode().getPointsEntryFile(state);
            }
            case null, default ->  {
                state.getMode().getDecreaseSocialCredits(state, update);
                state.getMode().getNotChoose(state);
            }
        };
    }
}
