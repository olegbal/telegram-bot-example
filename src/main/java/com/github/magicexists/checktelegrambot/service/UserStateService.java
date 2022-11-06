package com.github.magicexists.checktelegrambot.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserStateService {

  private final Map<Long, UserState> userStates = new HashMap<>();

  public UserState getUserStateById(Long id) {
    if (!userStates.containsKey(id)) {
      userStates.put(id, new UserState(id, "en", null, null));
    }

    return userStates.get(id);
  }

  public void resetUserState(Long id) {
    userStates.put(id, new UserState(id, "en", null, null));
  }
}
