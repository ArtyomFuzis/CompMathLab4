package com.fuzis.compmathlab4.MathLAB5.Methods;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.InterpolationMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class Bessel implements InterpolationMethod {

    @Override
    public Calculator.InterpolationResult getRes(ChatState state, List<Double> xs, List<Double> ys, double x_val, double[][] deltas) {
        int cind = (xs.size()-1) / 2;
        Function<Double, Double> f = (x)-> {
            double h = xs.get(1)-xs.get(0);
            double t = (x-xs.get(cind))/h;
            double powIter = t*(t-1);
            double res = (ys.get(cind)+ys.get(cind+1))/2+(t-0.5)*deltas[1][cind];
            for(int i=cind-1; i>0; i--){
                int k = cind - i;
                res +=  powIter / (2*k)  * (deltas[2*k][i]+deltas[2*k][i+1])/2;
                res += (t-0.5) *powIter / (2*k) / (2*k + 1) * deltas[2*k+1][i];
                powIter *= (t+k)*(t-k-1) / (2*k)/ (2*k+1);
            }
            return res;
        };
        var graphData = getGraphDataInter(x_val, xs, ys, f);
        return new Calculator.InterpolationResult(f.apply(x_val), graphData.xInter(), graphData.yInter(), xs, ys);
    }
}

