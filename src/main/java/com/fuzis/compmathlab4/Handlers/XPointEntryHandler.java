package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class XPointEntryHandler implements ResponseMethod {
    @Autowired
    public XPointEntryHandler(Calculator calc) {
        this.calc = calc;
    }
    Calculator calc;

    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())  {state.getMode().getNotChoose(state);state.getMode().getDecreaseSocialCredits(state, update);return;}
        try{
            double x = Double.parseDouble(update.getMessage().getText());
            calc.calc(x, state);
        }
        catch(NumberFormatException e) {
            state.getMode().getNotChoose(state);
        }
    }
}
