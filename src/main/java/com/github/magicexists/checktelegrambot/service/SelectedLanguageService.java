package com.github.magicexists.checktelegrambot.service;

public interface SelectedLanguageService {

  String getSelectedLanguage(String userId);

  void setUserLanguage(String userId, String languageCode);
}
