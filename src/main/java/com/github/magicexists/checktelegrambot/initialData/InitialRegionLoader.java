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
    regionRepository.save(new Region(null, "🇵🇱", "PLN", "pl"));
    regionRepository.save(new Region(null, "🇩🇪", "EUR", "de"));
  }
}
