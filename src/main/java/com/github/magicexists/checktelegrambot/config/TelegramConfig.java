package com.github.magicexists.checktelegrambot.config;

import com.github.magicexists.checktelegrambot.telegram.WriteReadBot;
import com.github.magicexists.checktelegrambot.telegram.handlers.callback.CallbackQueryHandler;
import com.github.magicexists.checktelegrambot.telegram.handlers.MessageHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class TelegramConfig {

  @Value("${telegram.webhook-path}")
  private String webhookPath;
  @Value("${telegram.bot-name}")
  private String botName;
  @Value("${telegram.bot-token}")
  private String botToken;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(webhookPath).build();
  }

  @Bean
  public WriteReadBot springWebhookBot(SetWebhook setWebhook,
      MessageHandler messageHandler,
      CallbackQueryHandler callbackQueryHandler) {
    WriteReadBot bot = new WriteReadBot(setWebhook, messageHandler, callbackQueryHandler);

    bot.setBotPath(webhookPath);
    bot.setBotUsername(botName);
    bot.setBotToken(botToken);

    return bot;
  }
}