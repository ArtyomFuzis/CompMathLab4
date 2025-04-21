package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Math.Approxes;
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

    @Override
    public void getReportGot(ChatState state) {
        sendCommunismPhoto("report", state);
        bot.sendMessage("<b>ЗАПРОШЕННЫЙ ОТЧЕТ</b>", state);
    }

    @Override
    public void getReportTitle(Approxes el,ChatState state) {
        String functype= switch (el){
            case Linear -> "ЛИНЕЙНАЯ";
            case Square -> "КВАДРАТИЧНАЯ";
            case Cube -> "КУБИЧЕСКАЯ";
            case Log -> "ЛОГАРИФМИЧЕСКАЯ";
            case Exp -> "ЭКСПОНЕНЦИАЛЬНАЯ";
            case Pow -> "СТЕПЕННАЯ";
        };
        bot.sendMessage("<b>ФУНКЦИЯ "+functype+"</b>", state);
    }

    @Override
    public void getReportPearson(double res,ChatState state) {
        String comment;
        if(res < 0.3) comment = "связь слабее даже союзников третьего рейха";
        else if(res < 0.5) comment = "связь умеренная (такая же умеренная, как и враги народа)";
        else if(res < 0.7) comment = "связь заметная (измерима штангенциркулем)";
        else if(res < 0.9) comment = "связь высока (как Останкинская телебашня)";
        else comment = "связь очень высока, как и ваша верность партии";
        bot.sendMessage("Коэффициент Пирсона: " + res+" - " +comment, state);
    }

    @Override
    public void getReportRS(double res, ChatState state) {
        String comment;
        if(res < 0.5) comment = "точность аппроксимации очень низка (такое нам не годится, товарищ)";
        else if(res < 0.75) comment = "точность аппроксимации низка (все ещё недостаточно для партии)";
        else if(res < 0.95) comment = "точность аппроксимации удовлетворительна (товарищ, старайтесь лучше)";
        else comment = "точность достаточна (хорошая работа, в будущем, возможно вам будет назначен повышенный социальный рейтинг)";
        bot.sendMessage("Коэффициент Детерминации: " + res+" - " +comment, state);
    }

    @Override
    public void getReportBest(Approxes maxApprox, ChatState state) {
        String functype= switch (maxApprox){
            case Linear -> "линейная";
            case Square -> "квадратичная";
            case Cube -> "кубическая";
            case Log -> "логарифмическая";
            case Exp -> "экспоненциальная";
            case Pow -> "степенная";
        };
        bot.sendMessage("Одна из лучших аппроксимирующих функций: " + functype, state);
    }

    @Override
    public void getReportEnd(ChatState state) {
        bot.sendMessage("Товарищ, отчет закончен. Не забудьте доложить о результатах вычислений в соответствующие органы.", state);
    }

    @Override
    public void getMakeSwitch(ChatState state) {
        bot.sendMessage("Товарищ, вы таки соизволили вернуться. Не забудьте занести явку с повинной в следственный комитет по месту жительства.", state);
    }
}
