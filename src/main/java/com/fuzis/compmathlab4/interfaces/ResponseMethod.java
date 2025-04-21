package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.ChatState;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

public interface ResponseMethod
{
    void handle(ChatState state, Update update);
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
    default void validatePoints(String input, ChatState state){
        var rows = input.trim().split("\n");
        if(rows.length != 2){ state.getMode().getPointsWrongRowsSize(state);return;}
        for(var row : rows){
            if(!row.matches("^\\s*(?:\\d+(?:[.,]\\d+)?\\s+){7,11}\\d+(?:[.,]\\d+)?\\s*$")){
                state.getMode().getPointsValidateError(state);
                return;
            }
        }
        Scanner scn = new Scanner(rows[0].replaceAll("\\.", ","));
        Scanner scn2 = new Scanner(rows[1].replaceAll("\\.", ","));
        ArrayList<Double> xs = new ArrayList<>();
        ArrayList<Double> ys = new ArrayList<>();
        HashSet<Double> xs_check = new HashSet<>();
        while(scn.hasNextDouble()){
            double cur_x = scn.nextDouble();
            xs.add(cur_x);
            xs_check.add(cur_x);
        }
        while(scn2.hasNextDouble()){
            ys.add(scn2.nextDouble());
        }
        if(xs.size() != ys.size()){ state.getMode().getPointsWrongRowsLength(state);return;}
        if(xs_check.size() != xs.size()) {state.getMode().getPointsSimlarPoints(state);return;}
        state.getMode().getPointsAccepted(state);
    }
}
