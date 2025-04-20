package com.fuzis.compmathlab4.Handlers;

import com.fuzis.compmathlab4.Bot;
import com.fuzis.compmathlab4.Transfer.UserState;
import com.fuzis.compmathlab4.interfaces.ResponseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

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
    public List<SendMessage> handle(UserState state, Update update) {
        if(!update.hasMessage()) return Collections.singletonList(state.getMode().getNotChoose());
        if(!update.getMessage().hasDocument()) return Collections.singletonList(state.getMode().getPointsNoFile());
        GetFile getFile = new GetFile();
        getFile.setFileId(update.getMessage().getDocument().getFileId());
        try {
            String filePath = bot.execute(getFile).getFilePath();
            File res = bot.downloadFile(filePath);
            return validatePoints(String.join("\n", Files.readAllLines(res.toPath())), state);
        }
        catch (TelegramApiException | IOException e){
            return Collections.singletonList(state.getMode().getPointsBadFile());
        }
    }
}
