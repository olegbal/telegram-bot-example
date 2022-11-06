package com.github.magicexists.checktelegrambot.repository;

import com.github.magicexists.checktelegrambot.domain.Region;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends MongoRepository<Region, ObjectId> {
  
}
