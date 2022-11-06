package com.github.magicexists.checktelegrambot.initialData;

import com.github.magicexists.checktelegrambot.domain.Region;
import com.github.magicexists.checktelegrambot.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitialRegionLoader {

  private final RegionRepository regionRepository;

  @PostConstruct
  public void initData() {
    regionRepository.deleteAll();
    regionRepository.save(new Region("pl", "🇵🇱", "PLN" ));
    regionRepository.save(new Region("de", "🇩🇪", "EUR"));
  }
}
