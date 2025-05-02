package com.fuzis.compmathlab4.MathLAB5;

import com.fuzis.compmathlab4.Data.ChatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Calculator {
    @Autowired
    public Calculator(ApplicationContext ctx) {
        this.ctx = ctx;
    }
    private ApplicationContext ctx;
    public void calculate(List<Double> xs, List<Double> ys, ChatState state) {
        state.getMode().getBroken(state);
    }
}
