package com.fuzis.compmathlab4;

import com.fuzis.compmathlab4.Data.ChatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

@Controller
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;

    public Handler handler;
    public Utils utils;

    public Bot(@Value("${bot.token}") String token,  @Autowired Handler handler, @Autowired Utils utils) {
        super(token);
        this.handler = handler;
        this.utils = utils;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() || update.hasCallbackQuery()) {
            handler.handleMessage(update);
        }
    }

    public void sendMessage(String text, ChatState state, ReplyKeyboard keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode(ParseMode.HTML);
        message.setChatId(state.getChatId());
        if(keyboardMarkup!=null) message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void sendMessage(String text, ChatState state) {
        sendMessage(text, state, null);
    }

    public void sendPhoto(File photo, ChatState state) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(state.getChatId());
        try {
            sendPhoto.setPhoto(new InputFile(photo));
            execute(sendPhoto);
        }
        catch (TelegramApiException | NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void sendRandomPhoto(String category, ChatState state) {
        sendPhoto(utils.getPhoto(category), state);
    }
    @Override
    public String getBotUsername() {
        return botName;
    }
}
