package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface InterpolationMethod
{
    Calculator.InterpolationResult getRes(ChatState state, List<Double> xs, List<Double> ys, double x, double[][] deltas);
    record GraphData(List<Double> xInter, List<Double> yInter){

    }
    int pointAmount = 1000;
    default GraphData getGraphDataInter(double x_val , List<Double> xs, List<Double> ys, Function<Double, Double> f){
        ArrayList<Double> xInter = new ArrayList<>();
        ArrayList<Double> yInter = new ArrayList<>();
        double l, r;
        if(x_val < xs.getFirst()){
            l = x_val;
            r = xs.getLast();
        }
        else if(x_val > xs.getLast()){
            l = xs.getFirst();
            r = x_val;
        }
        else{
            l = xs.getFirst();
            r = xs.getLast();
        }
        //System.out.println("l: " + l + ", r: " + r);
        double step = (r-l)/pointAmount;
        //System.out.println("step: " + step);
        for(double i = l; i < r ; i+=step){
            //System.out.println("work");
            xInter.add(i);
            yInter.add(f.apply(i));
        }
        return new GraphData(xInter, yInter);
    }
}
