package com.github.magicexists.checktelegrambot.constants.bot;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {
  //ответы на команды с клавиатуры

  SELECT_LANGUAGE_BUTTON("Выбрать язык"),
  SEND_CHECK_BUTTON("Отправить чек");
  private final String message;

  BotMessageEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
  }