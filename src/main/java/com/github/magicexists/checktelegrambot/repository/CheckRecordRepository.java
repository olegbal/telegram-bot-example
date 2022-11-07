package com.github.magicexists.checktelegrambot.repository;

import com.github.magicexists.checktelegrambot.domain.CheckRecord;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckRecordRepository extends MongoRepository<CheckRecord, ObjectId> {

  List<CheckRecord> getAllByDateBetweenAndRegionId(LocalDateTime startDate, LocalDateTime endDate,
      String regionId);
}
