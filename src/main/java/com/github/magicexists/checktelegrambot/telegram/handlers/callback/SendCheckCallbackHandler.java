package com.github.magicexists.checktelegrambot.telegram.handlers.callback;

import static com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper.SELECT_REGION_CALLBACK_PREFIX;

import com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@RequiredArgsConstructor
public class SendCheckCallbackHandler implements CustomCallBackHandler {

  public BotApiMethod<?> handle(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    final String data = buttonQuery.getData();

    return switch (data) {
      case String ignored
          && data.startsWith(CallbackNamesHelper.SEND_CHECK_CALLBACK_PREFIX
          + SELECT_REGION_CALLBACK_PREFIX) -> {
        yield new SendMessage(
            chatId, "Region selected! " + data.split("_")[2]);
      }
      default -> throw new IllegalStateException("Unexpected value: " + data);
    };
  }
}
