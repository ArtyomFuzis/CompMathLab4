package com.fuzis.compmathlab4.Data;

import com.fuzis.compmathlab4.Math.Approxes;

import java.util.HashMap;
import java.util.List;

public record CalcData(HashMap<Approxes, List<Double>> xs, HashMap<Approxes, List<Double>> ys, HashMap<Approxes, double[]>  ks, ChatState state, HashMap<Approxes, String> graphs, Counter got_graphs) {
    public static class Counter{
        public Counter(int val){
            this.val = val;
        }
        int val;
        public void increment(){
            val++;
        }
        public int val(){
            return val;
        }
    }
}
