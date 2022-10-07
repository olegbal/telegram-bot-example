package com.github.magicexists.checktelegrambot.telegram.handlers;

import com.github.magicexists.checktelegrambot.constants.bot.BotMessageEnum;
import com.github.magicexists.checktelegrambot.telegram.keyboards.ReplyKeyboardMaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

  @Autowired
  private ReplyKeyboardMaker replyKeyboardMaker;

  public BotApiMethod<?> answerMessage(Message message) {
    String chatId = message.getChatId().toString();

    String inputText = message.getText();

    if (inputText == null) {
      throw new IllegalArgumentException();
    } else if (inputText.equals("/start")) {
      return getStartMessage(chatId);
    } else {
      return new SendMessage(chatId, "Неизвестная команда");
    }
  }

  private SendMessage getStartMessage(String chatId) {
    SendMessage sendMessage = new SendMessage(chatId, "TEST START MESSAGE");
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
    return sendMessage;
  }
}