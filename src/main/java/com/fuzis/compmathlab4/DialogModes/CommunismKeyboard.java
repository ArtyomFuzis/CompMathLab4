package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Data.CallbackPair;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ModeKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class CommunismKeyboard implements ModeKeyboard
{
    public ReplyKeyboard getStartKeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Аппроксимация", DialogMode.CallBacks.START_CALC_LAB4),
                new CallbackPair("Интерполяция", DialogMode.CallBacks.START_CALC_LAB5),
                new CallbackPair("Motto kawaii desu?", DialogMode.CallBacks.SWITCH_MODE)
        );
    }
    public ReplyKeyboard getInputTypeKeyBoardLAB4(){
        return  createSingleRowKeyboard(
                new CallbackPair("Я буду набирать вручную", DialogMode.CallBacks.MANUALLY_WRITE_LAB4),
                new CallbackPair("Я отправлю файл", DialogMode.CallBacks.SEND_FILE_LAB4)
        );
    }
    public ReplyKeyboard getInputTypeKeyBoardLAB5(){
        return  createSingleRowKeyboard(
                new CallbackPair("Я буду набирать вручную", DialogMode.CallBacks.MANUALLY_WRITE_LAB5),
                new CallbackPair("Я отправлю файл", DialogMode.CallBacks.SEND_FILE_LAB5),
                new CallbackPair("Я воспользуюсь функцией", DialogMode.CallBacks.FUNC_WRITE_LAB5)
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
