package com.fuzis.compmathlab4.Math.approximations;

import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LinearApproximation implements MathApproximation {
    @Override
    public double[][] preSolve(double[] xs, double[] ys) {
        double sx = Arrays.stream(xs).sum();
        double sy = Arrays.stream(ys).sum();
        double sxx = Arrays.stream(xs).map(x->x*x).sum();
        double sxy = 0;
        int n = xs.length;
        for(int i=0; i<n; i++){
            sxy += xs[i]*ys[i];
        }
        return new double[][]{{n, sx, sy}, {sx, sxx, sxy}};
    }

    @Override
    public double[] applyFunc(double[] xs, double[] ks) {
        return Arrays.stream(xs).map(x->ks[0]+ks[1]*x).toArray();
    }

}
