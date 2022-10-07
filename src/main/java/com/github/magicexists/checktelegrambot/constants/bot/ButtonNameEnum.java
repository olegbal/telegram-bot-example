package com.github.magicexists.checktelegrambot.constants.bot;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
  SELECT_LANGUAGE_BUTTON("Выбрать язык"),
  SEND_CHECK_BUTTON("Отправить чек");
  private final String buttonName;

  ButtonNameEnum(String buttonName) {
    this.buttonName = buttonName;
  }

  public String getButtonName() {
    return buttonName;
  }
}