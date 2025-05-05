package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
import com.fuzis.compmathlab4.MathLAB5.Calculator;
import com.fuzis.compmathlab4.Utils;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class PointsEntryFileHandlerLAB5 implements ResponseMethod {
    @Autowired
    public PointsEntryFileHandlerLAB5(Utils utils, ApplicationContext ctx, Bot bot, Calculator calc) {
        this.ctx = ctx;
        this.bot = bot;
        this.calc = calc;
        this.utils = utils;
    }
    ApplicationContext ctx;
    Bot bot;
    Calculator calc;
    Utils utils;
    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage()) {state.getMode().getNotChoose(state);return;}
        if(!update.getMessage().hasDocument()) {state.getMode().getPointsNoFile(state);return;}
        GetFile getFile = new GetFile();
        getFile.setFileId(update.getMessage().getDocument().getFileId());
        try {
            String filePath = bot.execute(getFile).getFilePath();
            File res = bot.downloadFile(filePath);
            validatePointsAndSendLAB5(utils, String.join("\n", Files.readAllLines(res.toPath())), state, calc, ctx, update);
        }
        catch (TelegramApiException | IOException e){
            state.getMode().getPointsBadFile(state);
            state.getMode().getDecreaseSocialCredits(state, update);
        }
    }
}
