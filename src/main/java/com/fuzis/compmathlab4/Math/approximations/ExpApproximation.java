package com.fuzis.compmathlab4.Math.approximations;

import com.fuzis.compmathlab4.Data.SolveReturn;
import com.fuzis.compmathlab4.Math.CalcConductor;
import com.fuzis.compmathlab4.Messaging.Transfer.GraphRequest;
import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Component
public class ExpApproximation implements MathApproximation {
    @Override
    public double[][] preSolve(double[] xs, double[] ys) {
        return null;
    }

    @Override
    public SolveReturn solve(double[] xs, double[] ys, double[] solutions) {
        return null;
    }
}
