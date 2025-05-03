package com.fuzis.compmathlab4.MathLAB5;

import com.fuzis.compmathlab4.Data.ChatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Calculator {
    @Autowired
    public Calculator(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    private ApplicationContext ctx;
    record CalcData(ChatState state, List<Double> xs, List<Double> ys){

    }
    HashMap<Long, CalcData> data = new HashMap<>();
    public void calculateStart(List<Double> xs, List<Double> ys, ChatState state) {
        data.put(state.getChatId(), new CalcData(state, xs, ys));
        state.getMode().getBroken(state);
    }
}
