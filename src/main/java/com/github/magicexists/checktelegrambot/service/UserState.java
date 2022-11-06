package com.github.magicexists.checktelegrambot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserState {

  private Long userId;
  private String selectedLanguage;
  private String sendCheckSelectedRegion;
  private String sendCheckAmountTyped;
}
