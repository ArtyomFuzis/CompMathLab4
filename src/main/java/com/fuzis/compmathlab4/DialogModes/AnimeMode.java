package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.Math.Approxes;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimeMode implements DialogMode {
    @Autowired
    public AnimeMode(AnimeKeyboard board, Bot bot) {
        this.board = board;
        this.bot = bot;
    }
    Bot bot;
    AnimeKeyboard board;
    private void sendAnimePhoto(String subCategory, ChatState state){
        this.bot.sendRandomPhoto("anime\\"+subCategory, state);
    }
    @Override
    public void getStartMessage(ChatState state) {
        sendAnimePhoto("greeting", state);
        bot.sendMessage("Добро пожаловать в этот дивный мир, тут всегда вам рады, ня! ", state, board.getStartKeyBoard());
    }

    @Override
    public void getNotChoose(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Нуу, щто-то не так, давай ещё разок! ¬_¬", state);
    }

    @Override
    public void getStartCalculations(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Документы с собой? (●'◡'●)", state, board.getInputTypeKeyBoard());
    }

    @Override
    public void getSwitchMode(ChatState state) {
        sendAnimePhoto("switch", state);
        bot.sendMessage("Ты... Ты правда уходишь?! ~_~", state, board.switchBoard());
    }
    @Override
    public void getBroken(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("НяDeath", state);
    }

    @Override
    public void getSwitchCancelled(ChatState state) {
        bot.sendMessage("Уряяяя, ты остался. ヾ(≧▽≦*)o", state);
    }

    @Override
    public void getPointsEntry(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Хорошо, но давай тогда две строки из 8-12 значений ^_-", state);
    }

    @Override
    public void getPointsValidateError(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Нууу, ты щто-то не то ввел, давай-ка ещё разок (¬_¬ )", state);
    }

    @Override
    public void getPointsWrongRowsSize(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Две строфькиии хочу! ヽ（≧□≦）ノ", state);
    }

    @Override
    public void getPointsWrongRowsLength(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("А пофему количество значений разное? φ(*￣0￣)", state);
    }

    @Override
    public void getPointsAccepted(ChatState state) {
        sendAnimePhoto("wait", state);
        bot.sendMessage("Уря, все верно! o(*￣▽￣*)ブ", state);
    }

    @Override
    public void getPointsNoFile(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("А файлик где? ＞︿＜", state);
    }

    @Override
    public void getPointsEntryFile(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Тогда давайте их сюда! ヾ(≧▽≦*)o", state);
    }

    @Override
    public void getPointsBadFile(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Плохой файлик, хочу новый! ヽ（≧□≦）ノ", state);
    }

    @Override
    public void getPointsSimularPoints(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Ну что же ты, в функциях не бывает одинаковых аргументов! (╯‵□′)╯︵┻━┻", state);
    }

    @Override
    public void getReportGot(ChatState state) {
        sendAnimePhoto("report", state);
        bot.sendMessage("✨Отчётикс✨", state);
    }

    @Override
    public void getReportTitle(Approxes el,ChatState state) {
        String functype= switch (el){
            case Linear -> "линия";
            case Square -> "квадратная линия";
            case Cube -> "кубическая линия";
            case Log -> "логарифмик";
            case Exp -> "экспонента";
            case Pow -> "степень";
        };
        bot.sendMessage("ФунциNya: " + functype, state);
    }

    @Override
    public void getReportPearson(double res,ChatState state) {
        String comment;
        if(res < 0.3) comment = "слабоватенько";
        else if(res < 0.5) comment = "все равно с слабо";
        else if(res < 0.7) comment = "чуть сильней, чем слабо";
        else if(res < 0.9) comment = "связь неплоха";
        else comment = "связь большая";
        bot.sendMessage("Пирсон: " + res+" - " +comment, state);
    }

    @Override
    public void getReportRS(double res, ChatState state) {
        String comment;
        if(res < 0.5) comment = "точность мала.... ＞︿＜";
        else if(res < 0.75) comment = "плохо";
        else if(res < 0.95) comment = "неплохо";
        else comment = "круто";
        bot.sendMessage("Коэффициент Д№тер##н**и~: " + res+" - " +comment, state);
    }

    @Override
    public void getReportBest(Approxes maxApprox, ChatState state) {
        String functype= switch (maxApprox){
            case Linear -> "линия";
            case Square -> "квадратная линия";
            case Cube -> "кубическая линия";
            case Log -> "логарифмик";
            case Exp -> "экспонента";
            case Pow -> "степень";
        };
        bot.sendMessage("Лучшая ФунциNya: " + functype, state);
    }

    @Override
    public void getReportEnd(ChatState state) {
        bot.sendMessage("Готово, ня! (≧∇≦)ﾉ", state);
    }

    @Override
    public void getMakeSwitch(ChatState state) {
        bot.sendMessage("Уряяяя, ты выбрал правильную сторону! ☆*: .｡. o(≧▽≦)o .｡.:*☆", state);
    }
}
