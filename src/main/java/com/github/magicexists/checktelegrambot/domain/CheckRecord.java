package com.github.magicexists.checktelegrambot.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckRecord {
  
  @Id
  private ObjectId id;

}
