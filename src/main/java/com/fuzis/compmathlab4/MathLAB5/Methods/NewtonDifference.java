package com.fuzis.compmathlab4.MathLAB5.Methods;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.Utils;
import com.fuzis.compmathlab4.interfaces.InterpolationMethod;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Component
public class NewtonDifference implements InterpolationMethod
{
    HashMap<Utils.Pair<Integer, Integer>, Double> cache = new HashMap<>();
    private Double calculateSomeShit(LinkedList<Integer> args, List<Double> xs, List<Double> ys){
        if(args.size() == 2){
            var i1 = args.get(0);
            var i2 = args.get(1);
            return (ys.get(i2)-ys.get(i1))/(xs.get(i2)-xs.get(i1));
        }
        Integer xik = args.getLast();
        Integer xi = args.getFirst();
        args.removeFirst();
        if(!cache.containsKey(new Utils.Pair<>(args.getFirst(), args.getLast()))) {
            double f1 = calculateSomeShit(args, xs, ys);
            cache.put(new Utils.Pair<>(args.getFirst(), args.getLast()), f1);
        }
        double f1 = cache.get(new Utils.Pair<>(args.getFirst(), args.getLast()));
        args.addFirst(xi);
        args.removeLast();
        if(!cache.containsKey(new Utils.Pair<>(args.getFirst(), args.getLast()))) {
            double f2 = calculateSomeShit(args, xs, ys);
            cache.put(new Utils.Pair<>(args.getFirst(), args.getLast()), f2);
        }
        double f2 = cache.get(new Utils.Pair<>(args.getFirst(), args.getLast()));
        args.addLast(xik);
        return (f1 - f2)/(xs.get(xik) - xs.get(xi) );
    }
    public Calculator.InterpolationResult getRes(ChatState state, List<Double> xs, List<Double> ys, double x_val, double[][] deltas) {
        cache.clear();
        int n = xs.size();
        Function<Double, Double> f = (x)-> {
            double res = ys.getFirst();
            for(int i = 1; i < n; i++){
                if(!cache.containsKey(new Utils.Pair<>(0, i))) {
                    LinkedList<Integer> args = new LinkedList<>();
                    for (int j = 0; j <= i; j++) args.add(j);
                    double s = calculateSomeShit(args, xs, ys);
                    cache.put(new Utils.Pair<>(0, i), s);
                }
                double s = cache.get(new Utils.Pair<>(0, i));
                double p = 1;
                for(int j = 0; j < i; j++){
                    p *= x-xs.get(j);
                }
                res += p * s;
            }
            return res;
        };
        var graphData = getGraphDataInter(x_val, xs, ys, f);
        return new Calculator.InterpolationResult(f.apply(x_val), graphData.xInter(), graphData.yInter(), xs, ys);
    }
}
