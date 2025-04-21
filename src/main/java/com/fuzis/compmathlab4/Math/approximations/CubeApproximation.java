package com.fuzis.compmathlab4.Math.approximations;

import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CubeApproximation implements MathApproximation {
    @Override
    public double[][] preSolve(double[] xs, double[] ys) {
        double sx = Arrays.stream(xs).sum();
        double sy = Arrays.stream(ys).sum();
        double sxx = Arrays.stream(xs).map(x->x*x).sum();
        double sxxx = Arrays.stream(xs).map(x->x*x*x).sum();
        double sxxxx = Arrays.stream(xs).map(x->x*x*x*x).sum();
        double sxxxxx = Arrays.stream(xs).map(x->x*x*x*x*x).sum();
        double sxxxxxx = Arrays.stream(xs).map(x->x*x*x*x*x*x).sum();
        double sxy = 0;
        double sxxy = 0;
        double sxxxy = 0;
        int n = xs.length;
        for(int i=0; i<n; i++){
            sxy += xs[i]*ys[i];
            sxxy += xs[i]*xs[i]*ys[i];
            sxxxy += xs[i]*xs[i]*xs[i]*ys[i];
        }
        return new double[][]{
                {n, sx, sxx, sxxx, sy},
                {sx, sxx, sxxx, sxxxx, sxy},
                {sxx, sxxx, sxxxx, sxxxxx, sxxy},
                {sxxx, sxxxx, sxxxxx, sxxxxxx, sxxxy},

        };
    }

    @Override
    public double[] applyFunc(double[] xs, double[] ks) {
        return Arrays.stream(xs).map(x->ks[0]+ks[1]*x+ks[2]*x*x+ks[3]*x*x*x).toArray();
    }

}
