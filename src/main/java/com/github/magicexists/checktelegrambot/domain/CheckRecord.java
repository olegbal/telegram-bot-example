package com.github.magicexists.checktelegrambot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CheckRecord {

  @Id
  private ObjectId id;

  @Field
  private LocalDateTime date;

  @Field
  private String tgName;

  @Field
  private String tgUserName;

  @Field
  private Long tgUserId;

  @Field
  private BigDecimal amount;

  @DocumentReference
  private Region region;
}
