package com.github.magicexists.checktelegrambot.telegram.keyboards;

import static com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper.SELECT_REGION_CALLBACK_PREFIX;
import static com.github.magicexists.checktelegrambot.telegram.helpers.callbacks.CallbackNamesHelper.SEND_CHECK_CALLBACK_PREFIX;

import com.github.magicexists.checktelegrambot.domain.Region;
import com.github.magicexists.checktelegrambot.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SelectRegionKeyboard {

  private final RegionRepository regionRepository;

  public InlineKeyboardMarkup getSelectedRegionButtons() {

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    List<InlineKeyboardButton> sendCheckButtonRow = createSelectRegionButtons();

    rowList.add(sendCheckButtonRow);

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }

  private List<InlineKeyboardButton> createSelectRegionButtons() {

    List<Region> availableRegions = regionRepository.findAll();

    return availableRegions.stream()
        .map(region -> {
          InlineKeyboardButton regionButton = new InlineKeyboardButton();
          regionButton.setText(region.getName());
          regionButton.setCallbackData(
              SEND_CHECK_CALLBACK_PREFIX + SELECT_REGION_CALLBACK_PREFIX + region.code + ";"
                  + region.getCurrency());
          return regionButton;
        })
        .collect(Collectors.toList());
  }
}
