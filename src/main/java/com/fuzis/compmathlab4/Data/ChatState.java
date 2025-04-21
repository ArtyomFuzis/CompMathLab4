package com.fuzis.compmathlab4.Data;

import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;

public class ChatState
{
    public ChatState(DialogMode mode, ResponseMethod meth, Long chatId){
        this.mode = mode;
        this.meth = meth;
        this.chatId = chatId;
    }
    DialogMode mode;
    ResponseMethod meth;
    Long chatId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

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
}
