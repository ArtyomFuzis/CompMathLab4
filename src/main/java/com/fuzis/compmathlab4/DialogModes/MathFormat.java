package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.MathLAB5.FuncEntry;
import com.fuzis.compmathlab4.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MathFormat
{
    @Autowired
    public MathFormat(Utils utils, Bot bot, FuncEntry funcEntry){
        this.utils = utils;
        this.bot = bot;
        this.funcEntry = funcEntry;
    }
    FuncEntry funcEntry;
    Utils utils;
    Bot bot;
    public void sendTable(ChatState state, List<Double> xs, List<Double> ys, List<Double> phis, String graphUUID){
        bot.sendGraph(graphUUID, state);
        int n  = xs.size();
        StringBuilder builder = new StringBuilder();
        builder.append("x:  ");
        for (double x : xs) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        builder.append("y:  ");
        for (double x : ys) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        builder.append("φ:  ");
        for (double x : phis) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        double[] eps = new double[n];
        for(int i = 0; i < n; i++){
            eps[i] = ys.get(i)-phis.get(i);
        }
        builder.append("ε:  ");
        for (double x : eps) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        bot.sendMessage("<pre>"+ builder +"</pre>",state);
    }
    public void sendPearson(ChatState state, List<Double> xs, List<Double> ys){
        int n = xs.size();
        double exs = xs.stream().reduce(Double::sum).get()/n;
        double eys = ys.stream().reduce(Double::sum).get()/n;
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        for(int i = 0; i < n; i++){
            sum1 += (xs.get(i) - exs) * (ys.get(i) - eys);
            sum2 += (xs.get(i) - exs) * (xs.get(i) - exs);
            sum3 += (ys.get(i) - eys) * (ys.get(i)- eys);
        }
        double res = sum1 / Math.sqrt(sum2*sum3);
        state.getMode().getReportPearson(res,state);
    }
    public double sendRS(ChatState state, List<Double> ys, List<Double> phis){
        int n = ys.size();
        double ephis = phis.stream().reduce(Double::sum).get()/n;
        double sum1 = 0;
        double sum2 = 0;
        for(int i = 0; i < n; i++){
            sum1 += (ys.get(i) - phis.get(i)) * (ys.get(i) - phis.get(i));
            sum2 += (ys.get(i) - ephis) * (ys.get(i) - ephis);
        }
        double res = 1 - sum1 / sum2;
        state.getMode().getReportRS(res,state);
        return res;
    }
    public void sendFuncVariants(ChatState state){
        StringBuilder sb = new StringBuilder();
        int cntr = 1;
        for(var el : funcEntry.mathFunctions){
            sb.append(cntr++);
            sb.append(") ");
            sb.append(el.getStringRepr());
            sb.append("\n");
        }
        bot.sendMessage("<pre>"+ sb +"</pre>",state);
    }
    public void sendTableXY(ChatState state, List<Double> xs, List<Double> ys){
        StringBuilder builder = new StringBuilder();
        builder.append("x:  ");
        for (double x : xs) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        builder.append("y:  ");
        for (double x : ys) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        bot.sendMessage("<pre>"+ builder +"</pre>",state);
    }

    public void sendSumMatrix(ChatState state, double[][] deltas) {
        int n = deltas.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0; j < n-i; j++){
                sb.append(utils.getNSymbols(deltas[i][j], 8)).append(" ");
            }
            sb.append("\n");
        }
        bot.sendMessage("<pre>"+ sb +"</pre>",state);
    }
    public void sendCalcLab5(Double x_val, String graphUUID, ChatState state, Calculator.Interpolation interpolation){
        state.getMode().getResMethod(state, x_val, interpolation);
        bot.sendGraph(graphUUID, state);
    }
}
