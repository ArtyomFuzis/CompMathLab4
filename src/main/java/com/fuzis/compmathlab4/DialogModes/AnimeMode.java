package com.fuzis.compmathlab4.DialogModes;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB4.Approxes;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.interfaces.DialogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

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
    public void getStartCalculationsLAB4(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Документы с собой? (●'◡'●)", state, board.getInputTypeLAB4KeyBoard());
    }
    @Override
    public void getStartCalculationsLAB5(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Файлик аль функция?", state, board.getInputTypeLAB5KeyBoard());
    }
    @Override
    public void getPointsEntryFunc(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Ну и выбирай, с форматом ввода сам разберешься:", state);
    }

    @Override
    public void getWrongBordersChoose(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Границы кажись немного... Перевернутые....", state);
    }

    @Override
    public void getPointsFound(ChatState state) {
        bot.sendMessage("Точечьки:", state);
    }

    @Override
    public void getEnterX(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Иксик давай сюда!", state);
    }

    @Override
    public void getPointsEntryLAB5(ChatState state) {
        sendAnimePhoto("enter", state);
        bot.sendMessage("Введи точки (иксики в первой строке, игрики во второй)", state);
    }

    @Override
    public void getDifferentDifference(ChatState state) {
        sendAnimePhoto("validate", state);
        bot.sendMessage("Эта программка не поддерживает неравномерную сетку....", state);
    }

    @Override
    public void getSumMatrix(ChatState state) {
        sendAnimePhoto("report", state);
        bot.sendMessage("Матрица конечных разностей:", state);
    }

    @Override
    public void getResMethod(ChatState state, Double xVal, Calculator.Interpolation interpolation) {
        String xStr = String.format("%.5f", xVal);
        String method = switch (interpolation){
            case Lagrange -> "Лагранжа";
            case NewtonFixed -> "Ньютона для конечных сумм";
            case NewtonDifferent -> "Ньютона для разностных сумм";
            case Stirling -> "Стирлинга";
            case Bessel -> "Бесселя";
        };
        bot.sendMessage("Тут пришло значение: " + xStr+" (метод "+method+")", state);

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
    public void getPointsEntryLAB4(ChatState state) {
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
            case None -> "None";
        };
        bot.sendMessage("ФунциNya: " + functype, state);
    }

    @Override
    public void getReportPearson(double res, ChatState state) {
        String comment;
        double ares = Math.abs(res);
        if(ares < 0.3) comment = "слабоватенько";
        else if(ares < 0.5) comment = "все равно с слабо";
        else if(ares < 0.7) comment = "чуть сильней, чем слабо";
        else if(ares < 0.9) comment = "связь неплоха";
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
            case None -> "None";
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

    @Override
    public void getMeme(ChatState state) {
        bot.sendRandomPhoto("memes\\",state);
        bot.sendMessage("Мемчик!!!", state);
    }


    @Override
    public void getDecreaseSocialCredits(ChatState state, Update update) {
        Integer change = update.hasMessage()&&update.getMessage().hasText()&&update.getMessage().getText().trim().equals("/memes") ? 10: 1;
        bot.increaseStat(state, update, change);
    }

    @Override
    public void getBadChoose(ChatState state){
        sendAnimePhoto("validate", state);
        bot.sendMessage("Что это за чиселко то такое (номер функции)?!", state);
    }

}
