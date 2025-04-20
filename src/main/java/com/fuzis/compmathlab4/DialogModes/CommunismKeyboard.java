package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Transfer.CallbackPair;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ModeKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class CommunismKeyboard implements ModeKeyboard
{
    public ReplyKeyboard getStartKeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Приступить к вычислениям", DialogMode.CallBacks.START_CALC),
                new CallbackPair("Motto kawaii desu?", DialogMode.CallBacks.SWITCH_MODE)
        );
    }
    public ReplyKeyboard getInputTypeKeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Я буду набирать вручную", DialogMode.CallBacks.MANUALLY_WRITE),
                new CallbackPair("Я отправлю файл", DialogMode.CallBacks.SEND_FILE)
        );
    }
    public ReplyKeyboard switchBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("НЕТ", DialogMode.CallBacks.CANCEL_SWITCH),
                new CallbackPair("НЕТ", DialogMode.CallBacks.CANCEL_SWITCH),
                new CallbackPair("НЕТ", DialogMode.CallBacks.CANCEL_SWITCH)
        );
    }
}
