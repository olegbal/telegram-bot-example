package com.github.magicexists.checktelegrambot.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CachedSelectedLanguageServiceImpl implements
    SelectedLanguageService {

  private Map<String, String> selectedLanguagesMap;
  private MessageSource messageSource;

  @Override
  public String getSelectedLanguage(String userId) {
    return selectedLanguagesMap.getOrDefault(userId, "en");
  }

  @Override
  public void setUserLanguage(String userId, String languageCode) {
    selectedLanguagesMap.put(userId, languageCode);
  }
}
