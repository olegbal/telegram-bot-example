package com.github.magicexists.checktelegrambot.telegram.keyboards;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.Locale;

@Component
@AllArgsConstructor
public class ReplyKeyboardMaker {

  private MessageSource messageSource;

  public ReplyKeyboardMarkup getMainMenuKeyboard(String languageCode) {
    KeyboardRow row1 = new KeyboardRow();
    row1.add(new KeyboardButton(messageSource
            .getMessage("button.send.check", null, new Locale(languageCode))
        )
    );
    
    row1.add(new KeyboardButton(messageSource
            .getMessage("button.select.language", null, new Locale(languageCode))
        ));

    final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setKeyboard(List.of(row1));
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    return replyKeyboardMarkup;
  }
}