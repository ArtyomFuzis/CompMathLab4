package com.fuzis.compmathlab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class CompMathLab4Application {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(CompMathLab4Application.class, args);
    }

}
