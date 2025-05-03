package com.fuzis.compmathlab4.Handlers;


import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.MathFormat;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.MathLAB5.FuncEntry;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointsEntryFuncHandlerLAB5 implements ResponseMethod {
    @Autowired
    public PointsEntryFuncHandlerLAB5(ApplicationContext ctx, Bot bot, Calculator calc, FuncEntry funcEntry, MathFormat mathFormat) {
        this.ctx = ctx;
        this.bot = bot;
        this.calc = calc;
        this.funcEntry = funcEntry;
        this.mathFormat = mathFormat;
    }
    FuncEntry funcEntry;
    ApplicationContext ctx;
    MathFormat mathFormat;
    Bot bot;
    Calculator calc;
    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText()) {state.getMode().getNotChoose(state);return;}
        if(update.getMessage().getText().matches("^\\s*\\d+\\s+-?\\d+.?\\d*\\s+-?\\d+.?\\d*\\s+\\d+\\s*$")){
            try {
                var splitted = update.getMessage().getText().trim().split("\\s+");
                int choice = Integer.parseInt(splitted[0]);
                double a = Double.parseDouble(splitted[1]);
                double b = Double.parseDouble(splitted[2]);
                int cnt = Integer.parseInt(splitted[3]);
                if(choice <= 0 || choice > funcEntry.mathFunctions.size() || cnt == 0) {
                    state.getMode().getBadChoose(state);
                    return;
                }
                if(b <= a){
                    state.getMode().getWrongBordersChoose(state);
                    return;
                }
                ArrayList<Double> xs = new ArrayList<>();
                double step = (b - a) /cnt;
                for(double x = a; x <= b; x += step){
                    xs.add(x);
                }
                List<Double> ys = xs.stream().map(x -> funcEntry.mathFunctions.get(choice-1).getValue(x)).toList();
                state.getMode().getPointsFound(state);
                mathFormat.sendTableXY(state, xs, ys);
                state.getMode().getPointsAccepted(state);
                calc.calculateStart(xs, ys, state);
                state.setMeth(ctx.getBean(StartHandler.class));
            }
            catch(NumberFormatException e) {
                state.getMode().getPointsValidateError(state);
            }
        }
        else {
            state.getMode().getPointsValidateError(state);
        }

    }
}

