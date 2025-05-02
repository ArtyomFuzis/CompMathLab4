package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB4.Approxes;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface DialogMode
{
    void getStartMessage(ChatState state);
    void getNotChoose(ChatState state);
    void getStartCalculationsLAB4(ChatState state);
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

    void getReportPearson(double res, ChatState state);

    void getReportRS(double res, ChatState state);

    void getReportBest(Approxes maxApprox, ChatState state);
    void getReportEnd(ChatState state);

    void getMakeSwitch(ChatState state);

    void getMeme(ChatState state);
    void getBadChoose(ChatState state);
    void getDecreaseSocialCredits(ChatState state, Update update);
    void getStartCalculationsLAB5(ChatState state);
    void getPointsEntryFunc(ChatState state);

    void getWrongBordersChoose(ChatState state);

    void getPointsFound(ChatState state);

    enum CallBacks {
        START_CALC_LAB4,
        SWITCH_MODE,
        MANUALLY_WRITE_LAB4,MANUALLY_WRITE_LAB5,
        SEND_FILE_LAB4,SEND_FILE_LAB5,
        DO_SWITCH, CANCEL_SWITCH,START_CALC_LAB5, FUNC_WRITE_LAB5
    }
}
