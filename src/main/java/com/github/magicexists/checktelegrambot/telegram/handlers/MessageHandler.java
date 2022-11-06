package com.github.magicexists.checktelegrambot.telegram.handlers;

import com.github.magicexists.checktelegrambot.service.SelectedLanguageService;
import com.github.magicexists.checktelegrambot.telegram.keyboards.MainMenuKeyBoard;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageHandler {

  private MainMenuKeyBoard mainMenuKeyBoard;
  private MessageSource messageSource;
  private SelectedLanguageService selectedLanguageService;

  public BotApiMethod<?> answerMessage(Message message) {
    String chatId = message.getChatId().toString();
    String selectedLocale = selectedLanguageService
        .getSelectedLanguage(message.getFrom().getId().toString());

    if (message.getText() != null) {
      return switch (message.getText()) {
        case "/start" -> {
          SendMessage startMessage = new SendMessage(chatId,
              messageSource.getMessage("common.select.command", null,
                  new Locale(selectedLocale)));
          startMessage.setReplyMarkup(mainMenuKeyBoard.getMainMenuKeyboard(message));
          yield startMessage;
        }

        case String ignored && message.getReplyToMessage().getText()
            .startsWith("Enter amount in") -> {
          SendMessage attachCheckFileReply = new SendMessage(chatId, "Attach check file");
          attachCheckFileReply.setReplyMarkup(new ForceReplyKeyboard(true));
          yield attachCheckFileReply;
        }
        default -> new SendMessage(chatId,
            messageSource.getMessage("common.unknown.command", null,
                new Locale(selectedLocale)
            ));
      };
    } else {
      return switch (message) {
//      TODO ADD SOMETHING UNIVERSAL
        case Message ignore && message.getReplyToMessage().getText().equals("Attach check file")
            && !CollectionUtils.isEmpty(message.getPhoto()) -> {
          yield new SendMessage(chatId, "Check sent");
        }

        default -> new SendMessage(chatId,
            messageSource.getMessage("common.unknown.command", null,
                new Locale(selectedLocale)
            ));
      };
    }
  }
}