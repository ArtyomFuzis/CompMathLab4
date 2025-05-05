package com.fuzis.compmathlab4.Messaging.Transfer;

import com.fuzis.compmathlab4.MathLAB4.Approxes;

import java.util.List;

public record MessageToGraph(Integer type, Approxes approx, List<Double> xs, List<Double> ys, double[] ks,
                             Long chatId,
                             List<Double> dots_x, List<Double> dots_y, List<Double> inter_x, List<Double> inter_y) {
    public MessageToGraph(Approxes approx, List<Double> xs, List<Double> ys, double[] ks, Long chatId) {
        this(1, approx, xs, ys, ks, chatId, null, null, null, null);
    }
    public MessageToGraph(Long chatId, List<Double> dots_x, List<Double> dots_y, List<Double> inter_x, List<Double> inter_y) {
        this(2, null, null, null, null, chatId, dots_x, dots_y, inter_x, inter_y);
    }
}
