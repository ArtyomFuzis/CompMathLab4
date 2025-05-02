package com.fuzis.compmathlab4.MathLAB4;

import com.fuzis.compmathlab4.Data.CalcData;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.MathFormat;
import com.fuzis.compmathlab4.Handlers.StartResponseHandler;
import com.fuzis.compmathlab4.MathLAB4.approximations.*;
import com.fuzis.compmathlab4.Messaging.MessageService;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToGraph;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToSolve;
import com.fuzis.compmathlab4.interfaces.MathApproximation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CalcConductor
{
    @Autowired
    public CalcConductor(ApplicationContext ctx, MathFormat format, MessageService msgService, LinearApproximation linearApproximation, SquareApproximation squareApproximation, CubeApproximation cubeApproximation, ExpApproximation expApproximation, PowApproximation powApproximation, LogApproximation logApproximation){
        approxes = new HashMap<>();
        approxes.put(Approxes.Linear, linearApproximation);
        approxes.put(Approxes.Square, squareApproximation);
        approxes.put(Approxes.Cube, cubeApproximation);
        approxes.put(Approxes.Exp, expApproximation);
        approxes.put(Approxes.Pow, powApproximation);
        approxes.put(Approxes.Log, logApproximation);
        dataStore = new HashMap<>();
        this.msgService = msgService;
        this.format = format;
        this.ctx = ctx;
    }
    public final MessageService msgService;
    public final HashMap<Long, CalcData> dataStore;
    public final MathFormat format;
    public final ApplicationContext ctx;
    public HashMap<Approxes, MathApproximation> approxes;
    public void startCalculations(double[] xs, double[] ys, ChatState state){
        setCalcData(state.getChatId(), new CalcData(new HashMap<>(), new HashMap<>(), new HashMap<>(), state, new HashMap<>(), new CalcData.Counter(0)));
        for(var el : approxes.keySet()){
            CalcData calcData = getCalcData(state.getChatId());
            var res = approxes.get(el).preSolve(xs,ys);
            if(res.present()) {
                double[][] solveRequest = res.res();
                calcData.xs().put(el,res.xs());
                calcData.ys().put(el,res.ys());
                msgService.send_to_solve(new MessageToSolve(solveRequest), state.getChatId(), el);
            } else{
                incrementGraphCount(state.getChatId());
            }
        }
    }
    public synchronized void setCalcData(Long chatId, CalcData calcData){
        dataStore.put(chatId, calcData);
    }

    public synchronized CalcData getCalcData(Long chatId){
        return dataStore.get(chatId);
    }
    public synchronized void incrementGraphCount(Long chatId){
        CalcData calcData = dataStore.get(chatId);
        calcData.got_graphs().increment();
        if(calcData.got_graphs().val() == 6){
            sendRes(chatId);
        }
    }
    public void continueCalculations(double[] ks, Long chatId, Approxes approx){
        CalcData calcData = getCalcData(chatId);
        calcData.ks().put(approx, ks);
        msgService.send_to_graph(new MessageToGraph(approx, calcData.xs().get(approx), calcData.ys().get(approx), ks, chatId), chatId, approx);
    }
    public synchronized void loadGraph(String uuid, Approxes approx, Long chatId){
        CalcData calcData = dataStore.get(chatId);
        calcData.graphs().put(approx, uuid);
        incrementGraphCount(chatId);
    }
    public void sendRes(Long chatId){
        CalcData calcData = dataStore.get(chatId);
        calcData.state().getMode().getReportGot(calcData.state());
        Approxes max_approx = Approxes.Linear;
        double RS_MAX = 0;
        for(var el : calcData.graphs().keySet()){
            calcData.state().getMode().getReportTitle(el, calcData.state());
            List<Double> phis = approxes.get(el).applyFunc(calcData.xs().get(el), calcData.ks().get(el));
            format.sendTable(calcData.state(), calcData.xs().get(el), calcData.ys().get(el), phis, calcData.graphs().get(el));
            if(el == Approxes.Linear) format.sendPearson(calcData.state(), calcData.xs().get(el), calcData.ys().get(el));
            double RS = format.sendRS(calcData.state(), calcData.ys().get(el), phis);
            if(RS > RS_MAX) {
                RS_MAX = RS;
                max_approx = el;
            }
        }
        calcData.state().getMode().getReportBest(max_approx, calcData.state());
        calcData.state().getMode().getReportEnd(calcData.state());
        calcData.state().getMode().getStartMessage(calcData.state());
        calcData.state().setMeth(ctx.getBean(StartResponseHandler.class));
    }
}
