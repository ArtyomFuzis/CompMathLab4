package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.MathFormat;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCalcHandlerLAB5 implements ResponseMethod
{
    @Autowired
    public StartCalcHandlerLAB5(ApplicationContext ctx, MathFormat mathFormat) {
        this.ctx = ctx;
        this.mathFormat = mathFormat;
    }
    ApplicationContext ctx;
    MathFormat mathFormat;
    @Override
    public void handle(ChatState state, Update update) {
        switch (callbackOnly(update)) {
            case MANUALLY_WRITE_LAB5 -> {
                state.setMeth(ctx.getBean(PointsEntryHandlerLAB5.class));
                state.getMode().getPointsEntryLAB5(state);
            }
            case SEND_FILE_LAB5 -> {
                state.setMeth(ctx.getBean(PointsEntryFileHandlerLAB5.class));
                state.getMode().getPointsEntryFile(state);
            }
            case FUNC_WRITE_LAB5 -> {
                state.setMeth(ctx.getBean(PointsEntryFuncHandlerLAB5.class));
                state.getMode().getPointsEntryFunc(state);
                mathFormat.sendFuncVariants(state);
            }
            case null, default ->  {
                state.getMode().getDecreaseSocialCredits(state, update);
                state.getMode().getNotChoose(state);
            }
        };
    }
}
