package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Transfer.UserState;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PointsEntryHandler implements ResponseMethod
{
    @Autowired
    public PointsEntryHandler(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    ApplicationContext ctx;

    @Override
    public List<SendMessage> handle(UserState state, Update update) {
        if(!update.hasMessage()) return Collections.singletonList(state.getMode().getNotChoose());
        for(var row : update.getMessage().getText().split("\n")){
            if(row.matches("^\\s*(?:\\d+(?:[.,]\\d+)?\\s+){7,11}\\d+(?:[.,]\\d+)?\\s*$")){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
        return Collections.singletonList(state.getMode().getBroken());
    }
}
