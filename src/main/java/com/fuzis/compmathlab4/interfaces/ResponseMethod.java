package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Transfer.UserState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

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
    default List<SendMessage> validatePoints(String input, UserState state){
        var rows = input.trim().split("\n");
        if(rows.length != 2) return Collections.singletonList(state.getMode().getPointsWrongRowsSize());
        for(var row : rows){
            if(!row.matches("^\\s*(?:\\d+(?:[.,]\\d+)?\\s+){7,11}\\d+(?:[.,]\\d+)?\\s*$")){
                return Collections.singletonList(state.getMode().getPointsValidateError());
            }
        }
        Scanner scn = new Scanner(rows[0].replaceAll("\\.", ","));
        Scanner scn2 = new Scanner(rows[1].replaceAll("\\.", ","));
        ArrayList<Double> xs = new ArrayList<>();
        ArrayList<Double> ys = new ArrayList<>();
        while(scn.hasNextDouble()){
            xs.add(scn.nextDouble());
        }
        while(scn2.hasNextDouble()){
            ys.add(scn2.nextDouble());
        }
        if(xs.size() != ys.size()) return Collections.singletonList(state.getMode().getPointsWrongRowsLength());
        state.getArgs().add(xs);
        state.getArgs().add(ys);
        return Collections.singletonList(state.getMode().getPointsAccepted());
    }
}
