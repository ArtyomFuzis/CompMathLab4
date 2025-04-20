package com.fuzis.compmathlab4.Transfer;

import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;

import java.util.Stack;

public class UserState
{
    public UserState(DialogMode mode, ResponseMethod meth, Stack<Object> args){
        this.mode = mode;
        this.meth = meth;
        this.args = args;
    }
    DialogMode mode;
    ResponseMethod meth;
    Stack<Object> args;

    public DialogMode getMode() {
        return mode;
    }

    public void setMode(DialogMode mode) {
        this.mode = mode;
    }

    public ResponseMethod getMeth() {
        return meth;
    }

    public void setMeth(ResponseMethod meth) {
        this.meth = meth;
    }

    public Stack<Object> getArgs() {
        return args;
    }

    public void setArgs(Stack<Object> args) {
        this.args = args;
    }
}
