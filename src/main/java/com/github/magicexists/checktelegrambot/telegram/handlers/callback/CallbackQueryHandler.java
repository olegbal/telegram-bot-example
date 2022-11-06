package com.github.magicexists.checktelegrambot.telegram.handlers.callback;

import static com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper.MAIN_MENU_CALLBACK_PREFIX;
import static com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper.SEND_CHECK_CALLBACK_PREFIX;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

  private final List<CustomCallBackHandler> customCallBackHandlers;

  public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    final String data = buttonQuery.getData();

    return switch (data) {
      case String ignored
          && data.startsWith(MAIN_MENU_CALLBACK_PREFIX) -> findFittingHandler(
          MainMenuCallbackHandler.class).handle(buttonQuery);
      case String ignored
          && data.startsWith(SEND_CHECK_CALLBACK_PREFIX) -> findFittingHandler(
          SendCheckCallbackHandler.class).handle(buttonQuery);
      default -> new SendMessage(chatId, "Unknown button callback data");
    };
  }


  private CustomCallBackHandler findFittingHandler(
      Class<? extends CustomCallBackHandler> handlerClass) {
    return customCallBackHandlers.stream()
        .filter(handlerClass::isInstance)
        .findFirst().get();
  }

}