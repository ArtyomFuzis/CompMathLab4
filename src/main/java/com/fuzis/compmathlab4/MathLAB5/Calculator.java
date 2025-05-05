package com.fuzis.compmathlab4.MathLAB5;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.DialogModes.MathFormat;
import com.fuzis.compmathlab4.Handlers.StartHandler;
import com.fuzis.compmathlab4.Handlers.XPointEntryHandler;
import com.fuzis.compmathlab4.MathLAB5.Methods.Lagrange;
import com.fuzis.compmathlab4.MathLAB5.Methods.NewtonDifference;
import com.fuzis.compmathlab4.MathLAB5.Methods.NewtonFixed;
import com.fuzis.compmathlab4.Messaging.MessageService;
import com.fuzis.compmathlab4.Messaging.Transfer.MessageToGraph;
import com.fuzis.compmathlab4.interfaces.InterpolationMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Calculator {
    @Autowired
    public Calculator(ApplicationContext ctx, MathFormat format,
                      Lagrange lagrangeMeth,
                      MessageService messageService,
                      NewtonFixed newtonFixed, NewtonDifference newtonDifference) {
        this.ctx = ctx;
        this.format = format;
        interpolations = new HashMap<>();
        interpolations.put(Interpolation.Lagrange, lagrangeMeth);
        interpolations.put(Interpolation.NewtonFixed, newtonFixed);
        interpolations.put(Interpolation.NewtonDifferent, newtonDifference);
        this.messageService = messageService;
    }
    public enum Interpolation{
        Lagrange,
        NewtonFixed,
        NewtonDifferent,
        Stirling,
        Bessel
    }
    HashMap<Interpolation, InterpolationMethod> interpolations;
    private final ApplicationContext ctx;
    private final MathFormat format;
    private final MessageService messageService;
    record EnteredData(ChatState state, List<Double> xs, List<Double> ys, boolean fixed) {
    }
    public record InterpolationResult(double xRes, List<Double> xGraph,
                                      List<Double> yGraph,
                                      List<Double> interX,
                                      List<Double> interY) {

    }
    HashMap<Long, EnteredData> enteredData = new HashMap<>();

    public void calculateStart(List<Double> xs, List<Double> ys, ChatState state, boolean fixed) {
        enteredData.put(state.getChatId(), new EnteredData(state, xs, ys, fixed));
        state.getMode().getEnterX(state);
        state.setMeth(ctx.getBean(XPointEntryHandler.class));
    }
    record StoredData(ChatState state, Double x) {}
    HashMap<Long, HashMap<Interpolation, StoredData>> stored = new HashMap<>();
    public void calc(double x, ChatState state) {
        EnteredData entered = this.enteredData.get(state.getChatId());
        if(entered == null) {
            state.setMeth(ctx.getBean(StartHandler.class));
            state.getMode().getBroken(state);
        }
        else{
            int n = entered.xs.size();
            var ys = entered.ys;
            var xs = entered.xs;
            double[][] deltas = new double[n][n];
            for(int i = 0 ; i < n ; i ++){
                for(int j = 0; j < n-i; j++){
                    if(i == 0){
                        deltas[i][j] = ys.get(j);
                    }
                    else{
                        deltas[i][j] = deltas[i-1][j+1] - deltas[i-1][j];
                    }
                }
            }
            state.getMode().getSumMatrix(state);
            format.sendSumMatrix(state, deltas);
            if(entered.fixed()) {
                for (var key : interpolations.keySet()) {
                    var meth = interpolations.get(key);
                    var methRes = meth.getRes(state, xs, ys, x, deltas);
                    //System.out.println(methRes.xRes() + " " +methRes.yGraph().size()+ " " +methRes.xGraph().size()+ " " +methRes.interX().size()+ " " +methRes.interY().size());
                    if (!stored.containsKey(state.getChatId())) {
                        stored.put(state.getChatId(), new HashMap<>());
                    }
                    stored.get(state.getChatId()).put(key, new StoredData(state, methRes.xRes()));
                    messageService.send_to_graphLAB5(new MessageToGraph(state.getChatId(), methRes.xGraph(), methRes.yGraph(), methRes.interX(), methRes.interY()), state.getChatId(), key);
                }
            }
            else{
                var key = Interpolation.NewtonDifferent;
                var meth = interpolations.get(key);
                var methRes = meth.getRes(state, xs, ys, x, deltas);
                if (!stored.containsKey(state.getChatId())) {
                    stored.put(state.getChatId(), new HashMap<>());
                }
                stored.get(state.getChatId()).put(key, new StoredData(state, methRes.xRes()));
                messageService.send_to_graphLAB5(new MessageToGraph(state.getChatId(), methRes.xGraph(), methRes.yGraph(), methRes.interX(), methRes.interY()), state.getChatId(), key);
            }
        }
    }
    public synchronized void loadGraph(String uuid, Long chatId, Interpolation interpolation) {
        StoredData data = stored.get(chatId).get(interpolation);
        format.sendCalcLab5(data.x(), uuid, data.state(), interpolation);
        stored.get(chatId).remove(interpolation);
        data.state.setMeth(ctx.getBean(StartHandler.class));
    }
}
