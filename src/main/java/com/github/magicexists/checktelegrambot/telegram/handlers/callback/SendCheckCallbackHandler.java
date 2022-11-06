package com.github.magicexists.checktelegrambot.telegram.handlers.callback;

import static com.github.magicexists.checktelegrambot.telegram.helper.callbacks.CallbackNamesHelper.SELECT_REGION_CALLBACK_PREFIX;

import com.github.magicexists.checktelegrambot.service.UserState;
import com.github.magicexists.checktelegrambot.service.UserStateService;
import com.github.magicexists.checktelegrambot.telegram.helper.callbacks.CallbackNamesHelper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

@Component
@RequiredArgsConstructor
public class SendCheckCallbackHandler implements CustomCallBackHandler {

  private final UserStateService userStateService;

  public BotApiMethod<?> handle(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    final String data = buttonQuery.getData();

    return switch (data) {
      case String ignored
          && data.startsWith(CallbackNamesHelper.SEND_CHECK_CALLBACK_PREFIX
          + SELECT_REGION_CALLBACK_PREFIX) -> {
        String payload = data.split("_")[2];
        String countryCode = payload.split(";")[0];
        String currency = payload.split(";")[1];

        UserState userState = userStateService.getUserStateById(buttonQuery.getFrom().getId());
        userState.setSendCheckSelectedRegion(countryCode);

        SendMessage enterAmountMessage = new SendMessage(chatId, "Enter amount in "
            + currency);
        enterAmountMessage.setReplyMarkup(new ForceReplyKeyboard(true));
        
        yield enterAmountMessage;
      }
      default -> throw new IllegalStateException("Unexpected value: " + data);
    };
  }
}
