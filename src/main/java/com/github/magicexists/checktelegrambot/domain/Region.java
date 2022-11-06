package com.github.magicexists.checktelegrambot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Region {

  @Id
  public ObjectId id;

  @Field
  public String name;

  @Field
  public String currency;
  
  @Field
  public String code;
}
