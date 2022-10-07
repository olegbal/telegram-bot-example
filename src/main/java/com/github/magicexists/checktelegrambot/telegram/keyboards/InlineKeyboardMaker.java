package com.github.magicexists.checktelegrambot.telegram.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Клавиатуры, формируемые в ленте Telegram для получения файлов
 */
@Component
public class InlineKeyboardMaker {

  public InlineKeyboardMarkup getInlineMessageButtons() {
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    rowList.add(getButton("TEST 1", "TEST 1 CALLBACK DATA"));
    rowList.add(getButton("TEST 2", "TEST 2 CALLBACK DATA"));

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }

  private List<InlineKeyboardButton> getButton(String buttonName, String buttonCallBackData) {
    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText(buttonName);
    button.setCallbackData(buttonCallBackData);

    List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
    keyboardButtonsRow.add(button);
    return keyboardButtonsRow;
  }
}