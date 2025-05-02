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
                new CallbackPair("Помощь с вычислениями", DialogMode.CallBacks.START_CALC_LAB4),
                new CallbackPair("Чертовы Ньютоны", DialogMode.CallBacks.START_CALC_LAB5),
                new CallbackPair("Cummunism? ♂", DialogMode.CallBacks.SWITCH_MODE)
        );
    }
    public ReplyKeyboard getInputTypeLAB4KeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Неа", DialogMode.CallBacks.MANUALLY_WRITE_LAB4),
                new CallbackPair("Все с собой", DialogMode.CallBacks.SEND_FILE_LAB4)
        );
    }
    public ReplyKeyboard switchBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Да", DialogMode.CallBacks.DO_SWITCH),
                new CallbackPair("Нет", DialogMode.CallBacks.CANCEL_SWITCH)
        );
    }
    public ReplyKeyboard getInputTypeLAB5KeyBoard(){
        return  createSingleRowKeyboard(
                new CallbackPair("Мне бы файлик", DialogMode.CallBacks.SEND_FILE_LAB5),
                new CallbackPair("Я только руками", DialogMode.CallBacks.MANUALLY_WRITE_LAB5),
                new CallbackPair("Я казуальщик", DialogMode.CallBacks.FUNC_WRITE_LAB5)
        );
    }
}
