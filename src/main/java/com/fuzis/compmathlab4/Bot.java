package com.fuzis.compmathlab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Controller
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;

    public Handler handler;

    public Bot(@Value("${bot.token}") String token,  @Autowired Handler handler) {
        super(token);
        this.handler = handler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() || update.hasCallbackQuery()) {
            try {
                var res = handler.handleMessage(update);
                for(var el : res) {
                    this.execute(el);
                }
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
