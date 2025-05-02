package com.fuzis.compmathlab4.Math.approximations;

import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LinearApproximation implements MathApproximation {
    @Override
    public ApproxRes preSolve(double[] xs, double[] ys) {
        double sx = Arrays.stream(xs).sum();
        double sy = Arrays.stream(ys).sum();
        double sxx = Arrays.stream(xs).map(x->x*x).sum();
        double sxy = 0;
        int n = xs.length;
        for(int i=0; i<n; i++){
            sxy += xs[i]*ys[i];
        }
        return new ApproxRes(new double[][]{{n, sx, sy}, {sx, sxx, sxy}}, Arrays.stream(xs).boxed().toList(), Arrays.stream(ys).boxed().toList());
    }

    @Override
    public List<Double> applyFunc(List<Double> xs, double[] ks) {
        return xs.stream().map(x->ks[0]+ks[1]*x).toList();
    }

}
