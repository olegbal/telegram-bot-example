package com.github.magicexists.checktelegrambot.telegram.handlers;

import com.github.magicexists.checktelegrambot.domain.CheckRecord;
import com.github.magicexists.checktelegrambot.domain.Region;
import com.github.magicexists.checktelegrambot.repository.CheckRecordRepository;
import com.github.magicexists.checktelegrambot.repository.RegionRepository;
import com.github.magicexists.checktelegrambot.service.SynchronizedTotalCounter;
import com.github.magicexists.checktelegrambot.service.UserState;
import com.github.magicexists.checktelegrambot.service.UserStateService;
import com.github.magicexists.checktelegrambot.telegram.TelegramApiClient;
import com.github.magicexists.checktelegrambot.telegram.keyboards.MainMenuKeyBoard;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@Component
@AllArgsConstructor
public class MessageHandler {

  private MainMenuKeyBoard mainMenuKeyBoard;
  private MessageSource messageSource;
  private UserStateService userStateService;
  private CheckRecordRepository checkRecordRepository;
  private RegionRepository regionRepository;
  private TelegramApiClient telegramApiClient;
  private SynchronizedTotalCounter synchronizedTotalCounter;

  public BotApiMethod<?> answerMessage(Message message) {
    String chatId = message.getChatId().toString();

    if (message.getText() != null) {
      return switch (message.getText()) {
        case "/start" -> {
          SendMessage startMessage = new SendMessage(chatId,
              messageSource.getMessage("common.select.command", null,
                  new Locale("en")));
          startMessage.setReplyMarkup(mainMenuKeyBoard.getMainMenuKeyboard(message));
          yield startMessage;
        }

//      TODO ADD SOMETHING UNIVERSAL
        case String ignored && Objects.nonNull(message.getReplyToMessage())
            && message.getReplyToMessage().getText()
            .startsWith("Enter amount in") -> {

          try {
            BigDecimal bigDecimal = new BigDecimal(message.getText());
          } catch (NumberFormatException ex) {
            userStateService.resetUserState(message.getFrom().getId());

            yield new SendMessage(chatId, "Incorrect number, please type '/start' to try again");
          }

          UserState userState = userStateService.getUserStateById(message.getFrom().getId());
          userState.setSendCheckAmountTyped(message.getText());

          SendMessage attachCheckFileReply = new SendMessage(chatId, "Attach check file");
          attachCheckFileReply.setReplyMarkup(new ForceReplyKeyboard(true));
          yield attachCheckFileReply;
        }

        default -> new SendMessage(chatId,
            messageSource.getMessage("common.unknown.command", null,
                new Locale("en")
            ));
      };
    } else {
      return switch (message) {
//      TODO ADD SOMETHING UNIVERSAL
        case Message ignore && message.getReplyToMessage().getText().equals("Attach check file")
            && !CollectionUtils.isEmpty(message.getPhoto()) -> {
          String imageId = message.getPhoto().get(0).getFileId();
          User fromUser = message.getFrom();
          Long userId = fromUser.getId();
          String userName = fromUser.getUserName();
          String fullName = fromUser.getFirstName() + " " + fromUser.getLastName();

          UserState userState = userStateService.getUserStateById(fromUser.getId());
          Region region = regionRepository.findById(userState.getSendCheckSelectedRegion()).get();

          telegramApiClient.sendImageWithCaption(imageId,
              MessageFormat.format("Sent by {0} (@{1}).\n\rAmount: {2}.\n\rRegion: {3}",
                  fullName, userName,
                  userState.getSendCheckAmountTyped() + " " + region.getCurrency(),
                  region.getId() + " " + region.getName()));

          checkRecordRepository.save(new CheckRecord(null,
              LocalDateTime.now(),
              fullName, userName, userId, new BigDecimal(userState.getSendCheckAmountTyped()),
              region));

          yield new SendMessage(chatId, "Check sent");
        }

        default -> new SendMessage(chatId,
            messageSource.getMessage("common.unknown.command", null,
                new Locale("en")
            ));
      };
    }
  }
}