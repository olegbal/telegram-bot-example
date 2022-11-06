package com.github.magicexists.checktelegrambot.telegram.handlers.callback;

import static com.github.magicexists.checktelegrambot.telegram.helper.callbacks.CallbackNamesHelper.MAIN_MENU_CALLBACK_PREFIX;

import com.github.magicexists.checktelegrambot.service.UserStateService;
import com.github.magicexists.checktelegrambot.telegram.keyboards.SelectRegionKeyboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@RequiredArgsConstructor
public class MainMenuCallbackHandler implements CustomCallBackHandler {

  private final SelectRegionKeyboard selectRegionKeyboard;
  private final UserStateService userStateService;

  public BotApiMethod<?> handle(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    final String data = buttonQuery.getData();

    return switch (data) {
      case MAIN_MENU_CALLBACK_PREFIX + "selectLanguageAction" -> new SendMessage(chatId,
          "TODO add multi language support");
      case MAIN_MENU_CALLBACK_PREFIX + "sendCheckAction" -> {
        SendMessage selectRegionMessage = new SendMessage(chatId, "Select region");
        selectRegionMessage.setReplyMarkup(selectRegionKeyboard.getSelectedRegionButtons());

        yield selectRegionMessage;
      }
      default -> throw new IllegalStateException("Unexpected value: " + data);
    };
  }

}
