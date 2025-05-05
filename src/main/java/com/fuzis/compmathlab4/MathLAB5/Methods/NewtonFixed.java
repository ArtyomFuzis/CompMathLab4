package com.fuzis.compmathlab4.MathLAB5.Methods;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.InterpolationMethod;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
public class NewtonFixed implements InterpolationMethod
{
    public Calculator.InterpolationResult getRes(ChatState state, List<Double> xs, List<Double> ys, double x_val, double[][] deltas) {
        int n = xs.size();
        Function<Double, Double> f = (x)-> {
            int ind = Collections.binarySearch(xs, x);
            if(ind >=0) return ys.get(ind);
            ind = -ind - 1;
            boolean rightChoose;
            if(ind == n){
                rightChoose = true;
            }
            else if(ind == 0){
                rightChoose = false;
            }
            else{
                rightChoose = Math.abs(xs.get(ind)-x) < Math.abs(xs.get(ind-1)-x);
            }
            double h = xs.get(1)-xs.get(0);
            double t = rightChoose ?(x-xs.getLast())/h : (x-xs.getFirst())/h;
            double powIter = 1;
            double res = 0;
            if(rightChoose){
                for(int i=0; i<n; i++){
                    res+=deltas[i][n-i-1] * powIter;
                    powIter *= (t+i)/(i+1);
                }
            } else{
                for(int i=0; i<n; i++){
                    res+=deltas[i][0] * powIter;
                    powIter *= (t-i)/(i+1);
                }
            }
            return res;
        };
        var graphData = getGraphDataInter(x_val, xs, ys, f);
        return new Calculator.InterpolationResult(f.apply(x_val), graphData.xInter(), graphData.yInter(), xs, ys);
    }
}
