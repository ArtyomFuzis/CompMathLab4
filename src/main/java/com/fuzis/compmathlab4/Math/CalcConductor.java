package com.fuzis.compmathlab4.Math;

import com.fuzis.compmathlab4.Data.CalcData;
import com.fuzis.compmathlab4.Math.approximations.*;
import com.fuzis.compmathlab4.Messaging.MessageService;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToSolve;
import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CalcConductor
{
    @Autowired
    public CalcConductor(MessageService msgService, LinearApproximation linearApproximation, SquareApproximation squareApproximation, CubeApproximation cubeApproximation, ExpApproximation expApproximation, PowApproximation powApproximation, LogApproximation logApproximation){
        approxes = new HashMap<>();
        approxes.put(Approxes.Linear, linearApproximation);
        approxes.put(Approxes.Square, squareApproximation);
        approxes.put(Approxes.Cube, cubeApproximation);
        approxes.put(Approxes.Exp, expApproximation);
        approxes.put(Approxes.Pow, powApproximation);
        approxes.put(Approxes.Log, logApproximation);
        _instance = new SendCollector();
        dataStore = new HashMap<>();
        this.msgService = msgService;
    }
    public final MessageService msgService;
    public final SendCollector _instance;
    public final HashMap<Long, CalcData> dataStore;
    public HashMap<Approxes, MathApproximation> approxes;
    public void startCalculations(double[] xs, double[] ys, Long chatId){
        for(var el : approxes.keySet()){
            setCalcData(chatId, new CalcData(xs, ys));
            double[][] solveRequest = approxes.get(el).preSolve(xs,ys);
            msgService.send_to_solve(new MessageToSolve(solveRequest), chatId, el);
        }
    }
    public synchronized void setCalcData(Long chatId, CalcData calcData){
        dataStore.put(chatId, calcData);
    }

    public synchronized  CalcData getCalcData(Long chatId){
        return dataStore.get(chatId);
    }

    public static class SendCollector{

    }
}
