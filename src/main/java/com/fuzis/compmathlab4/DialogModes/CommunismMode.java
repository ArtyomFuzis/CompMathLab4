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

    @Override
    public void getStartMessage(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Здравствуйте, товарищ! Что вы хотите сделать?\n<b>Товарищ майор уже следит за вами</b>");
        message.setReplyMarkup(board.getStartKeyBoard());
        bot.sendMessage(message, state);
    }

    @Override
    public void getNotChoose(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("<b>Товарищ, будьте благоразумны!</b>\nНе пытайтесь сломать результат " +
                "труда народа, используйте только последние предложенные варианты!");
        bot.sendMessage(message, state);
    }

    @Override
    public void getStartCalculations(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Пожалуйста, товарищ, выберите какой метод ввода вы хотите использовать:");
        message.setReplyMarkup(board.getInputTypeKeyBoard());
        bot.sendMessage(message, state);
    }

    @Override
    public void getSwitchMode(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ!!!!\nВы в своем уме?! Даже предатель родины бы на такое бы не пошел! Вы правда желаете этого?");
        message.setReplyMarkup(board.switchBoard());
        bot.sendMessage(message, state);
    }
    @Override
    public void getBroken(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("К сожалению, произошла неизвестная ошибка, не забудьте доложить компартии об этом. Текущее состояние сброшено.");
        bot.sendMessage(message, state);
    }

    @Override
    public void getSwitchCancelled(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("<b>Вы сделали правильный выбор</b>");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsEntry(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Пожалуйста, товарищ, введите от 8 до 12 пар чисел в две строки через пробел. " +
                "На первой строке должны располагаться значения x, на второй y.");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsValidateError(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, к сожалению ваш ввод не является <s>полит</s>корректным. " +
                "Пожалуйста, проверьте что вы все вводите правильно!");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsWrongRowsSize(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, вы должны ввести именно две строки!");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsWrongRowsLength(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, строки должны содержать одинаковое количество значений!!!");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsAccepted(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, партия гордится вами! Вы успешно ввели точки, ожидайте обработки...");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsNoFile(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, пожалуйста, прикрепите файл, не испытывайте терпение партии!");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsEntryFile(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Хорошо, товарищ. Теперь, пожалуйста, прикрепите файл:");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsBadFile(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("Вы загрузили какой-то странный файл, загрузите новый. Партия не может ждать вечно.");
        bot.sendMessage(message, state);
    }

    @Override
    public void getPointsSimlarPoints(ChatState state) {
        SendMessage message = new SendMessage();
        message.setText("В вашем наборе данных есть одинаковые значения для x. Для функции такое не допустимо, товарищ, исправьте!");
        bot.sendMessage(message, state);
    }
}
