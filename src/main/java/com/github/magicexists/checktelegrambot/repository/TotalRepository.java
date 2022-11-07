package com.github.magicexists.checktelegrambot.repository;

import com.github.magicexists.checktelegrambot.domain.Total;
import com.github.magicexists.checktelegrambot.domain.TotalType;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TotalRepository extends MongoRepository<Total, ObjectId> {

  List<Total> getAllByDateAndType(LocalDate localDate, TotalType totalType);
}
