package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface DialogMode
{
    void getStartMessage(ChatState state);
    void getNotChoose(ChatState state);
    void getStartCalculations(ChatState state);
    void getSwitchMode(ChatState state);
    void getBroken(ChatState state);
    void getSwitchCancelled(ChatState state);
    void getPointsEntry(ChatState state);
    void getPointsValidateError(ChatState state);
    void getPointsWrongRowsSize(ChatState state);

    void getPointsWrongRowsLength(ChatState state);

    void getPointsAccepted(ChatState state);

    void getPointsNoFile(ChatState state);

    void getPointsEntryFile(ChatState state);

    void getPointsBadFile(ChatState state);

    void getPointsSimlarPoints(ChatState state);

    enum CallBacks {
        START_CALC,
        SWITCH_MODE,
        MANUALLY_WRITE,
        SEND_FILE,
        CANCEL_SWITCH
    }
}
