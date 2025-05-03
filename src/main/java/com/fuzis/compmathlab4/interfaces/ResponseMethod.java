package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Handlers.StartHandler;
import com.fuzis.compmathlab4.MathLAB4.CalcConductor;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

public interface ResponseMethod
{
    record Points(List<Double> xs, List<Double> ys){ }
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
    default void validatePointsAndSendLAB4(String input, ChatState state, CalcConductor calcConductor, ApplicationContext ctx, Update update){
        var res = validate(input, state, update, true);
        if(res == null)return;
        var xs = res.xs;
        var ys = res.ys;
        calcConductor.startCalculations(xs.stream().mapToDouble(x -> x).toArray(), ys.stream().mapToDouble(x -> x).toArray(), state);
        state.getMode().getPointsAccepted(state);
        state.setMeth(ctx.getBean(StartHandler.class));
    }
    default void validatePointsAndSendLAB5(String input, ChatState state, Calculator calc, ApplicationContext ctx, Update update){
        var res = validate(input, state, update, false);
        if(res == null)return;
        var xs = res.xs;
        var ys = res.ys;
        calc.calculateStart(xs, ys, state);
        state.getMode().getPointsAccepted(state);
        state.setMeth(ctx.getBean(StartHandler.class));
    }
    default Points validate(String input, ChatState state, Update update, boolean cntValidate){
        var rows = input.trim().split("\n");
        var regex = cntValidate ? "^\\s*(?:-?\\d+(?:[.,]\\d+)?\\s+){7,11}-?\\d+(?:[.,]\\d+)?\\s*$" : "^\\s*(?:-?\\d+(?:[.,]\\d+)?\\s+)*-?\\d+(?:[.,]\\d+)?\\s*$";
        if(rows.length != 2){ state.getMode().getPointsWrongRowsSize(state);state.getMode().getDecreaseSocialCredits(state, update);return null;}
        for(var row : rows){
            if(!row.matches(regex)){
                state.getMode().getPointsValidateError(state);
                state.getMode().getDecreaseSocialCredits(state, update);
                return null;
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
        if(xs.size() != ys.size()){ state.getMode().getPointsWrongRowsLength(state);state.getMode().getDecreaseSocialCredits(state, update);return null;}
        if(xs_check.size() != xs.size()) {state.getMode().getPointsSimularPoints(state);state.getMode().getDecreaseSocialCredits(state, update);return null;}
        return new Points(xs, ys);
    }
}
