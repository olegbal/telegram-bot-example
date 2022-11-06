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
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Total {

  @Id
  private ObjectId id;

  @Field
  private TotalType type;

  @Field
  private LocalDate date;

  @Field
  private BigDecimal amount;

  @Field
  @DocumentReference
  private Region region;
}
