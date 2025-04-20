package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Transfer.UserState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public interface ResponseMethod
{
    List<SendMessage> handle(UserState state, Update update);
    default DialogMode.CallBacks callbackOnly(Update update){
        if(!update.hasCallbackQuery()){
            return null;
        }
        try {
            return DialogMode.CallBacks.valueOf(update.getCallbackQuery().getData());
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }
}
