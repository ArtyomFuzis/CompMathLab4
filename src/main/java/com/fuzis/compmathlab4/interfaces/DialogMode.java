package com.fuzis.compmathlab4.interfaces;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface DialogMode
{
    SendMessage getStartMessage();
    SendMessage getNotChoose();
    SendMessage getStartCalculations();
    SendMessage getSwitchMode();
    SendMessage getBroken();
    SendMessage getSwitchCancelled();
    SendMessage getPointsEntry();

    enum CallBacks{
        START_CALC,
        SWITCH_MODE,
        MANUALLY_WRITE,
        SEND_FILE,
        CANCEL_SWITCH
    }
}
