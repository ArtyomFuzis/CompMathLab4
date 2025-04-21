package com.fuzis.compmathlab4.Data;

import com.fuzis.compmathlab4.Math.Approxes;

import java.util.HashMap;

public record CalcData(double[] xs, double[] ys, HashMap<Approxes, double[]>  ks, ChatState state) {
}
