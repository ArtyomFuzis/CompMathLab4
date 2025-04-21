package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.SolveReturn;
import com.fuzis.compmathlab4.Messaging.Transfer.GraphRequest;

public interface MathApproximation
{
    double[][] preSolve(double[] xs, double[] ys);
    SolveReturn solve(double[] xs, double[] ys, double[] solutions);
}
