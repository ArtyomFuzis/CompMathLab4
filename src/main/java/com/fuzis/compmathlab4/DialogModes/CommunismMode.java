package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.interfaces.DialogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
public class CommunismMode implements DialogMode {
    @Autowired
    public CommunismMode(CommunismKeyboard board) {
        this.board = board;
    }

    CommunismKeyboard board;
    @Override
    public SendMessage getStartMessage() {
        SendMessage message = new SendMessage();
        message.setText("Здравствуйте, товарищ! Что вы хотите сделать?\n<b>Товарищ майор уже следит за вами</b>");
        message.setReplyMarkup(board.getStartKeyBoard());
        return message;
    }

    @Override
    public SendMessage getNotChoose() {
        SendMessage message = new SendMessage();
        message.setText("<b>Товарищ, будьте благоразумны!</b>\nНе пытайтесь сломать результат " +
                "труда народа, используйте только последние предложенные варианты!");
        return message;
    }

    @Override
    public SendMessage getStartCalculations() {
        SendMessage message = new SendMessage();
        message.setText("Пожалуйста, товарищ, выберите какой метод ввода вы хотите использовать:");
        message.setReplyMarkup(board.getInputTypeKeyBoard());
        return message;
    }

    @Override
    public SendMessage getSwitchMode() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ!!!!\nВы в своем уме?! Даже предатель родины бы на такое бы не пошел! Вы правда желаете этого?");
        message.setReplyMarkup(board.switchBoard());
        return message;
    }
    @Override
    public SendMessage getBroken() {
        SendMessage message = new SendMessage();
        message.setText("К сожалению, произошла неизвестная ошибка, не забудьте доложить компартии об этом. Текущее состояние сброшено.");
        return message;
    }

    @Override
    public SendMessage getSwitchCancelled() {
        SendMessage message = new SendMessage();
        message.setText("<b>Вы сделали правильный выбор</b>");
        return message;
    }

    @Override
    public SendMessage getPointsEntry() {
        SendMessage message = new SendMessage();
        message.setText("Пожалуйста, товарищ, введите от 8 до 12 пар чисел в две строки через пробел. " +
                "На первой строке должны располагаться значения x, на второй y.");
        return message;
    }

    @Override
    public SendMessage getPointsValidateError() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, к сожалению ваш ввод не является <s>полит</s>корректным. " +
                "Пожалуйста, проверьте что вы все вводите правильно!");
        return message;
    }

    @Override
    public SendMessage getPointsWrongRowsSize() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, вы должны ввести именно две строки!");
        return message;
    }

    @Override
    public SendMessage getPointsWrongRowsLength() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, строки должны содержать одинаковое количество значений!!!");
        return message;
    }

    @Override
    public SendMessage getPointsAccepted() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, партия гордится вами! Вы успешно ввели точки, ожидайте обработки...");
        return message;
    }

    @Override
    public SendMessage getPointsNoFile() {
        SendMessage message = new SendMessage();
        message.setText("Товарищ, пожалуйста, прикрепите файл, не испытывайте терпение партии!");
        return message;
    }

    @Override
    public SendMessage getPointsEntryFile() {
        SendMessage message = new SendMessage();
        message.setText("Хорошо, товарищ. Теперь, пожалуйста, прикрепите файл:");
        return message;
    }

    @Override
    public SendMessage getPointsBadFile() {
        SendMessage message = new SendMessage();
        message.setText("Вы загрузили какой-то странный файл, загрузите новый. Партия не может ждать вечно.");
        return message;
    }
}
