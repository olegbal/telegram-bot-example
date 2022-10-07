package com.github.magicexists.checktelegrambot.telegram.keyboards;

import com.github.magicexists.checktelegrambot.service.SelectedLanguageService;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Клавиатуры, формируемые в ленте Telegram для получения файлов
 */
@Component
@AllArgsConstructor
public class MainMenuKeyBoard {

  private MessageSource messageSource;
  private SelectedLanguageService selectedLanguageService;
  private Set<String> availableLocales;

  public InlineKeyboardMarkup getMainMenuKeyboard(Message message) {
    String userLocale = selectedLanguageService.getSelectedLanguage(
        message.getFrom().getId().toString());
    
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    List<InlineKeyboardButton> row1 = new ArrayList<>();

    InlineKeyboardButton sendCheckButton = createSendCheckButton(userLocale);
    InlineKeyboardButton selectLanguageButton = createSelectLanguageButton(userLocale);
    row1.addAll(List.of(sendCheckButton, selectLanguageButton));
    
    
    rowList.add(row1);
    
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }

  private InlineKeyboardButton createSelectLanguageButton(String userLocale) {
    InlineKeyboardButton selectLanguageButton = new InlineKeyboardButton();
    selectLanguageButton.setText(messageSource.getMessage("button.select.language", null,
        new Locale(userLocale)));
    selectLanguageButton.setCallbackData("selectLanguageAction");
    return selectLanguageButton;
  }

  private InlineKeyboardButton createSendCheckButton(String userLocale) {
    InlineKeyboardButton sendCheckButton = new InlineKeyboardButton();
    sendCheckButton.setText(messageSource.getMessage("button.send.check", null,
        new Locale(userLocale)));
    sendCheckButton.setCallbackData("sendCheckAction");
    return sendCheckButton;
  }

  public InlineKeyboardMarkup getAvailableLanguages(Message message) {
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    List<InlineKeyboardButton> row1 = availableLocales.stream()
        .map(code -> {
          InlineKeyboardButton button = new InlineKeyboardButton();
          button.setText(code);
          button.setCallbackData(code);
          return button;
        }).collect(Collectors.toList());

    rowList.add(row1);
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}