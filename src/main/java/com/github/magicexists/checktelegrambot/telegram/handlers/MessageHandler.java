package com.github.magicexists.checktelegrambot.telegram.handlers;

import com.github.magicexists.checktelegrambot.service.SelectedLanguageService;
import com.github.magicexists.checktelegrambot.telegram.keyboards.MainMenuKeyBoard;
import com.github.magicexists.checktelegrambot.telegram.keyboards.ReplyKeyboardMaker;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageHandler {

  private ReplyKeyboardMaker replyKeyboardMaker;
  private MainMenuKeyBoard mainMenuKeyBoard;
  private MessageSource messageSource;
  private SelectedLanguageService selectedLanguageService;

  public BotApiMethod<?> answerMessage(Message message) {
    String chatId = message.getChatId().toString();
    String selectedLocale = selectedLanguageService
        .getSelectedLanguage(message.getFrom().getId().toString());

    switch (message.getText()) {
      case "/start":
        SendMessage sendMessage = new SendMessage(chatId,
            messageSource.getMessage("common.select.command", null,
                new Locale(selectedLocale)
            ));
        sendMessage.setReplyMarkup(mainMenuKeyBoard.getMainMenuKeyboard(message));
        return sendMessage;
      default:
        return new SendMessage(chatId,
            messageSource.getMessage("common.unknown.command", null,
                new Locale(selectedLocale)
            )
        );
    }
  }
}