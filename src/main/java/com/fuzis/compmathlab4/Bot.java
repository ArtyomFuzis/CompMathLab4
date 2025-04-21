package com.fuzis.compmathlab4;

import com.fuzis.compmathlab4.Data.ChatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
            handler.handleMessage(update);
        }
    }

    public void sendMessage(SendMessage message, ChatState state) {
        message.setParseMode(ParseMode.HTML);
        message.setChatId(state.getChatId());
        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return botName;
    }
}
