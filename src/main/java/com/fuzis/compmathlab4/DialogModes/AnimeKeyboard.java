package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Data.CallbackPair;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import com.fuzis.compmathlab4.interfaces.ModeKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class AnimeKeyboard implements ModeKeyboard
{
    public ReplyKeyboard getStartKeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Помощь с вычислениями", DialogMode.CallBacks.START_CALC),
                new CallbackPair("Cummunism? ♂", DialogMode.CallBacks.SWITCH_MODE)
        );
    }
    public ReplyKeyboard getInputTypeKeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Неа", DialogMode.CallBacks.MANUALLY_WRITE),
                new CallbackPair("Все с собой", DialogMode.CallBacks.SEND_FILE)
        );
    }
    public ReplyKeyboard switchBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Да", DialogMode.CallBacks.DO_SWITCH),
                new CallbackPair("Нет", DialogMode.CallBacks.CANCEL_SWITCH)
        );
    }
}
