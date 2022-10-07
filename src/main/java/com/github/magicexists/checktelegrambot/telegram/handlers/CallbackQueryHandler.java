package com.github.magicexists.checktelegrambot.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.IOException;

@Component
public class CallbackQueryHandler {

  public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
    final String chatId = buttonQuery.getMessage().getChatId().toString();

    String data = buttonQuery.getData();

    switch (data) {
      case "selectLanguageAction":
        new SendMessage(chatId, "Select Language received");
      case "sendCheckAction":
        new SendMessage(chatId, "Send check received");
      default:
        return new SendMessage(chatId, "TEST FROM CALLBACK HANDLER");
    }
  }
}