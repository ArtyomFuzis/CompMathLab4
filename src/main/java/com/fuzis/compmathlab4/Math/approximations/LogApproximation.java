package com.fuzis.compmathlab4.Math.approximations;

import com.fuzis.compmathlab4.Utils;
import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LogApproximation implements MathApproximation {
    @Autowired
    public LogApproximation(Utils utils) {
        this.utils = utils;
    }
    Utils utils;
    @Override
    public ApproxRes preSolve(double[] preXs, double[] preYs) {
        var zipped = utils.zipCollections(Arrays.stream(preXs).boxed().toList(), Arrays.stream(preYs).boxed().toList());
        var fZipped = zipped.stream().filter(z -> z.a() > 0).toList();
        if(fZipped.isEmpty())return new ApproxRes();
        var xs = fZipped.stream().map(Utils.Pair::a).toList();
        var ys = fZipped.stream().map(Utils.Pair::b).toList();
        double sx = xs.stream().map(Math::log).reduce(Double::sum).get();
        double sy = ys.stream().reduce(Double::sum).get();
        double sxx = xs.stream().map(Math::log).map(x->x*x).reduce(Double::sum).get();
        double sxy = 0;
        int n = xs.size();
        for(int i=0; i<n; i++){
            sxy += Math.log(xs.get(i))*ys.get(i);
        }
        return new ApproxRes(new double[][]{{n, sx, sy}, {sx, sxx, sxy}}, xs, ys);
    }

    @Override
    public List<Double> applyFunc(List<Double> xs, double[] ks) {
        return xs.stream().map(x->Math.log(x)*ks[1]+ks[0]).toList();
    }

}
