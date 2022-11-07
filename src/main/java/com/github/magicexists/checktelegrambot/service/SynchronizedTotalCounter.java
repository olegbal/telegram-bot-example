package com.github.magicexists.checktelegrambot.service;

import com.github.magicexists.checktelegrambot.domain.CheckRecord;
import com.github.magicexists.checktelegrambot.domain.Region;
import com.github.magicexists.checktelegrambot.domain.Total;
import com.github.magicexists.checktelegrambot.domain.TotalType;
import com.github.magicexists.checktelegrambot.repository.CheckRecordRepository;
import com.github.magicexists.checktelegrambot.repository.RegionRepository;
import com.github.magicexists.checktelegrambot.repository.TotalRepository;
import com.github.magicexists.checktelegrambot.telegram.TelegramApiClient;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SynchronizedTotalCounter {

  private final CheckRecordRepository checkRecordRepository;
  private final TotalRepository totalRepository;
  private final RegionRepository regionRepository;
  private final TelegramApiClient telegramApiClient;


  @Scheduled(cron = "0 0 0 * * *")
  public void calculateTotal() {
    List<Region> allRegions = regionRepository.findAll();

    allRegions.forEach(region -> {
      List<CheckRecord> regionChecks = checkRecordRepository.getAllByDateBetweenAndRegionId(
          LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0),
          LocalDateTime.now().withHour(0).withMinute(0).withSecond(0), region.getId());

      BigDecimal total = regionChecks.stream().map(CheckRecord::getAmount)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
      totalRepository.save(
          new Total(null, TotalType.DAY, LocalDate.now().minusDays(1), total, region));

//      TODO CALCULATE weekly
//      TODO CALCULATE monthly
    });

    List<Total> resultTotals = totalRepository.getAllByDateAndType(
        LocalDate.now().minusDays(1), TotalType.DAY);

    StringBuilder stringBuilder = new StringBuilder(
        "Total amounts " + LocalDate.now().minusDays(1) + ": \n\r");

    resultTotals.forEach(regionTotal -> {
      stringBuilder
          .append("Region: ").append(regionTotal.getRegion().getId())
          .append(" ").append(regionTotal.getRegion().getName()).append(". ").append("Amount: ")
          .append(regionTotal.getAmount()).append(" ")
          .append(regionTotal.getRegion().getCurrency()).append(".\n\r");
    });

    telegramApiClient.sendText(stringBuilder.toString());
  }
}
