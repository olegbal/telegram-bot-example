package com.github.magicexists.checktelegrambot.telegram.handlers.callback;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CustomCallBackHandler {

  public BotApiMethod<?> handle(CallbackQuery buttonQuery);
}
