package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Math.Approxes;

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

    void getPointsSimularPoints(ChatState state);
    void getReportGot(ChatState state);

    void getReportTitle(Approxes el, ChatState state);

    void getReportPearson(double res,ChatState state);

    void getReportRS(double res, ChatState state);

    void getReportBest(Approxes maxApprox, ChatState state);
    void getReportEnd(ChatState state);

    void getMakeSwitch(ChatState state);

    enum CallBacks {
        START_CALC,
        SWITCH_MODE,
        MANUALLY_WRITE,
        SEND_FILE,
        DO_SWITCH, CANCEL_SWITCH
    }
}
