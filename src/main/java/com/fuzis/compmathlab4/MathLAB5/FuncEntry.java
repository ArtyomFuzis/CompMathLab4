package com.fuzis.compmathlab4.MathLAB5;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FuncEntry {
    public interface EntryMathFunction {
        String getStringRepr();
        double getValue(double x);
    }
    public List<EntryMathFunction> mathFunctions = Arrays.asList(new EntryMathFunction(){
        @Override
        public String getStringRepr() {
            return "sin(x)";
        }

        @Override
        public double getValue(double x) {
            return Math.sin(x);
        }
    }, new EntryMathFunction(){

        @Override
        public String getStringRepr() {
            return "5*x^2-10x-15";
        }

        @Override
        public double getValue(double x) {
            return 5*x*x-10*x-15;
        }
    }, new EntryMathFunction(){
        @Override
        public String getStringRepr() {
            return "exp(x)";
        }

        @Override
        public double getValue(double x) {
            return Math.exp(x);
        }
    });
}
