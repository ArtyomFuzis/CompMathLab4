package com.fuzis.compmathlab4.Messaging.Transfer;

import com.fuzis.compmathlab4.MathLAB4.Approxes;

import java.util.List;

public record MessageToGraph(Approxes approx, List<Double> xs, List<Double> ys, double[] ks, Long chatId) {
}
