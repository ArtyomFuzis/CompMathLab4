package com.fuzis.compmathlab4.interfaces;

public interface MathApproximation
{
    double[][] preSolve(double[] xs, double[] ys);
    double[] applyFunc(double[] xs, double[] ks);
}
