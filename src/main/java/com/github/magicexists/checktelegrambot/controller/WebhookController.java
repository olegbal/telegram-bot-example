package com.github.magicexists.checktelegrambot.controller;

import com.github.magicexists.checktelegrambot.telegram.WriteReadBot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {
  
  private final WriteReadBot writeReadBot;

  public WebhookController(WriteReadBot writeReadBot) {
    this.writeReadBot = writeReadBot;
  }

  @PostMapping("/")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return writeReadBot.onWebhookUpdateReceived(update);
  }
}
