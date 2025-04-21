package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
public class CommunismMode implements DialogMode {
    @Autowired
    public CommunismMode(CommunismKeyboard board, Bot bot) {
        this.board = board;
        this.bot = bot;
    }
    Bot bot;
    CommunismKeyboard board;
    private void sendCommunismPhoto(String subCategory, ChatState state){
        this.bot.sendRandomPhoto("soviet\\"+subCategory, state);
    }
    @Override
    public void getStartMessage(ChatState state) {
        sendCommunismPhoto("greeting", state);
        bot.sendMessage("Здравствуйте, товарищ! Что вы хотите сделать?\n<s><b>Товарищ майор уже следит за вами</b></s>", state, board.getStartKeyBoard());
    }

    @Override
    public void getNotChoose(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("<b>Товарищ, будьте благоразумны!</b>\nНе пытайтесь сломать результат " +
                "труда народа, используйте только последние предложенные варианты!", state);
    }

    @Override
    public void getStartCalculations(ChatState state) {
        sendCommunismPhoto("enter", state);
        bot.sendMessage("Пожалуйста, товарищ, выберите какой метод ввода вы хотите использовать:", state, board.getInputTypeKeyBoard());
    }

    @Override
    public void getSwitchMode(ChatState state) {
        sendCommunismPhoto("switch", state);
        bot.sendMessage("Товарищ!!!!\nВы в своем уме?! Даже предатель родины бы на такое бы не пошел! Вы правда желаете этого?", state, board.switchBoard());
    }
    @Override
    public void getBroken(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("К сожалению, произошла неизвестная ошибка, не забудьте доложить компартии об этом. Текущее состояние сброшено.", state);
    }

    @Override
    public void getSwitchCancelled(ChatState state) {
        bot.sendMessage("<b>Вы сделали правильный выбор</b>", state);
    }

    @Override
    public void getPointsEntry(ChatState state) {
        sendCommunismPhoto("enter", state);
        bot.sendMessage("Пожалуйста, товарищ, введите от 8 до 12 пар чисел в две строки через пробел. " +
                "На первой строке должны располагаться значения x, на второй y.", state);
    }

    @Override
    public void getPointsValidateError(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("Товарищ, к сожалению ваш ввод не является <s>полит</s>корректным. " +
                "Пожалуйста, проверьте что вы все вводите правильно!", state);
    }

    @Override
    public void getPointsWrongRowsSize(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("Товарищ, вы должны ввести именно две строки!", state);
    }

    @Override
    public void getPointsWrongRowsLength(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("Товарищ, строки должны содержать одинаковое количество значений!!!", state);
    }

    @Override
    public void getPointsAccepted(ChatState state) {
        sendCommunismPhoto("wait", state);
        bot.sendMessage("Товарищ, партия гордится вами! Вы успешно ввели точки, ожидайте обработки...", state);
    }

    @Override
    public void getPointsNoFile(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("Товарищ, пожалуйста, прикрепите файл, не испытывайте терпение партии!", state);
    }

    @Override
    public void getPointsEntryFile(ChatState state) {
        sendCommunismPhoto("enter", state);
        bot.sendMessage("Хорошо, товарищ. Теперь, пожалуйста, прикрепите файл:", state);
    }

    @Override
    public void getPointsBadFile(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("Вы загрузили какой-то странный файл, загрузите новый. Партия не может ждать вечно.", state);
    }

    @Override
    public void getPointsSimularPoints(ChatState state) {
        sendCommunismPhoto("validate", state);
        bot.sendMessage("В вашем наборе данных есть одинаковые значения для x. Для функции такое не допустимо, товарищ, исправьте!", state);
    }
}
