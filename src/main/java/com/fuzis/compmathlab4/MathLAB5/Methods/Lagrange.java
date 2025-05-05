package com.fuzis.compmathlab4.MathLAB5.Methods;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.InterpolationMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
public class Lagrange implements InterpolationMethod
{
    public Calculator.InterpolationResult getRes(ChatState state, List<Double> xs, List<Double> ys, double x_val, double[][] deltas) {
        int n = xs.size();
        ArrayList<Double> lower_pow = new ArrayList<>(n);
        for(int i = 0; i < n; ++i){
            lower_pow.add(1.0);
            for(int j = 0; j < n; ++j){
                if(i == j)  continue;
                lower_pow.set(i, lower_pow.get(i) * (xs.get(i) - xs.get(j)));
            }
        }
        Function<Double, Double> f = (x)-> {
            double res = 0;
            int ind = Collections.binarySearch(xs, x);
            if(ind >= 0) return ys.get(ind);
            double upper_pow = 1;
            for(int i = 0; i < n; i++) {
                upper_pow = upper_pow * (x-xs.get(i));
            }
            for(int i = 0 ; i < n ; i++){
                res += ys.get(i)*upper_pow/lower_pow.get(i)/(x-xs.get(i));
            }
            return res;
        };
        var graphData = getGraphDataInter(x_val, xs, ys, f);
        return new Calculator.InterpolationResult(f.apply(x_val), graphData.xInter(), graphData.yInter(), xs, ys);
    }
}
