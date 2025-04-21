package com.fuzis.compmathlab4.Messaging.Transfer;

import com.fuzis.compmathlab4.Math.Approxes;

public record MessageToGraph(Approxes approx, double[] xs, double[] ys, double[] ks, Long chatId) {
}
