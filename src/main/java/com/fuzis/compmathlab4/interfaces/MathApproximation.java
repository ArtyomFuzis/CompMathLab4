package com.fuzis.compmathlab4.interfaces;

import java.util.List;

public interface MathApproximation {
    ApproxRes preSolve(double[] xs, double[] ys);

    List<Double> applyFunc(List<Double> xs, double[] ks);

    record ApproxRes(boolean present, double[][] res, List<Double> xs, List<Double> ys){
        public ApproxRes(){
            this(false, null, null, null);
        }
        public ApproxRes(double[][] res, List<Double> xs, List<Double> ys){
            this(true, res, xs, ys);
        }
    }
}
