package com.fuzis.compmathlab4.Math;

import com.fuzis.compmathlab4.Data.CalcData;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Math.approximations.*;
import com.fuzis.compmathlab4.Messaging.MessageService;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToGraph;
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
        dataStore = new HashMap<>();
        this.msgService = msgService;
    }
    public final MessageService msgService;
    public final HashMap<Long, CalcData> dataStore;
    public HashMap<Approxes, MathApproximation> approxes;
    public void startCalculations(double[] xs, double[] ys, ChatState state){
        for(var el : approxes.keySet()){
            setCalcData(state.getChatId(), new CalcData(xs, ys, new HashMap<>(), state));
            double[][] solveRequest = approxes.get(el).preSolve(xs,ys);
            msgService.send_to_solve(new MessageToSolve(solveRequest), state.getChatId(), el);
        }
    }
    public synchronized void setCalcData(Long chatId, CalcData calcData){
        dataStore.put(chatId, calcData);
    }

    public synchronized  CalcData getCalcData(Long chatId){
        return dataStore.get(chatId);
    }
    public void continueCalculations(double[] ks, Long chatId, Approxes approx){
        CalcData calcData = getCalcData(chatId);
        calcData.ks().put(approx, ks);
        msgService.send_to_graph(new MessageToGraph(approx, calcData.xs(), calcData.ys(), ks, chatId), chatId, approx);
    }
}
