package com.github.magicexists.checktelegrambot.telegram.keyboards;

import com.github.magicexists.checktelegrambot.constants.bot.ButtonNameEnum;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Основная клавиатура, расположенная под строкой ввода текста в Telegram
 */
@Component
public class ReplyKeyboardMaker {

  public ReplyKeyboardMarkup getMainMenuKeyboard() {
    KeyboardRow row1 = new KeyboardRow();
    row1.add(new KeyboardButton(ButtonNameEnum.SEND_CHECK_BUTTON.getButtonName()));
    row1.add(new KeyboardButton(ButtonNameEnum.SELECT_LANGUAGE_BUTTON.getButtonName()));

    List<KeyboardRow> keyboard = new ArrayList<>();
    keyboard.add(row1);

    final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setKeyboard(keyboard);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    return replyKeyboardMarkup;
  }
}