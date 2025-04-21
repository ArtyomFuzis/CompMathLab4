package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class MathFormat
{
    @Autowired
    public MathFormat(Utils utils, Bot bot){
        this.utils = utils;
        this.bot = bot;
    }
    Utils utils;
    Bot bot;
    public void sendTable(ChatState state, double[] xs, double[] ys, double[] phis, String graphUUID){
        bot.sendGraph(graphUUID, state);
        int n  = xs.length;
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
            eps[i] = ys[i]-phis[i];
        }
        builder.append("ε:  ");
        for (double x : eps) builder.append("|").append(utils.getNSymbols(x, 8));
        builder.append("|\n");
        bot.sendMessage("<pre>"+ builder +"</pre>",state);
    }
    public void sendPearson(ChatState state, double[] xs, double[] ys){
        int n = xs.length;
        double exs = Arrays.stream(xs).sum()/n;
        double eys = Arrays.stream(ys).sum()/n;
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        for(int i = 0; i < n; i++){
            sum1 += (xs[i] - exs) * (ys[i] - eys);
            sum2 += (xs[i] - exs) * (xs[i] - exs);
            sum3 += (ys[i] - eys) * (ys[i] - eys);
        }
        double res = sum1 / Math.sqrt(sum2*sum3);
        state.getMode().getReportPearson(res,state);
    }
    public double sendRS(ChatState state, double[] ys, double[] phis){
        int n = ys.length;
        double ephis = Arrays.stream(phis).sum()/n;
        double sum1 = 0;
        double sum2 = 0;
        for(int i = 0; i < n; i++){
            sum1 += (ys[i] - phis[i]) * (ys[i] - phis[i]);
            sum2 += (ys[i] - ephis) * (ys[i] - ephis);
        }
        double res = 1 - sum1 / sum2;
        state.getMode().getReportRS(res,state);
        return res;
    }
}
