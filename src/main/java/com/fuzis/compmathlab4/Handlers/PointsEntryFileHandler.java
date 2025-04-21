package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Data.ChatState;
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
import java.util.Collections;

@Component
public class PointsEntryFileHandler implements ResponseMethod {
    @Autowired
    public PointsEntryFileHandler(ApplicationContext ctx, Bot bot) {
        this.ctx = ctx;
        this.bot = bot;
    }
    ApplicationContext ctx;
    Bot bot;

    @Override
    public void handle(ChatState state, Update update) {
        if(!update.hasMessage()) {state.getMode().getNotChoose(state);return;}
        if(!update.getMessage().hasDocument()) {state.getMode().getPointsNoFile(state);return;}
        GetFile getFile = new GetFile();
        getFile.setFileId(update.getMessage().getDocument().getFileId());
        try {
            String filePath = bot.execute(getFile).getFilePath();
            File res = bot.downloadFile(filePath);
            validatePoints(String.join("\n", Files.readAllLines(res.toPath())), state);
        }
        catch (TelegramApiException | IOException e){
            state.getMode().getPointsBadFile(state);
        }
    }
}
