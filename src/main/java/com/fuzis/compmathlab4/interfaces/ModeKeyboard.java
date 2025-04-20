package com.fuzis.compmathlab4.interfaces;

import com.fuzis.compmathlab4.Transfer.CallbackPair;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.LinkedList;

public interface ModeKeyboard
{
    default InlineKeyboardMarkup createSingleRowKeyboard(CallbackPair... text){
        LinkedList<InlineKeyboardButton> buttons = new LinkedList<>();
        for(var el : text){
            var btn = new InlineKeyboardButton(el.text());
            btn.setCallbackData(el.callback().name());
            buttons.add(btn);
        }
        return InlineKeyboardMarkup.builder().keyboardRow(buttons).build();
    }
}
