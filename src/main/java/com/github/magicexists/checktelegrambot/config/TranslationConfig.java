package com.github.magicexists.checktelegrambot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Set;

@Configuration
public class TranslationConfig {

  @Bean
  public Set<String> availableLocales() {
    return Set.of("ru", "pl", "en");
  }

  @Bean
  public MessageSource messageSource() {

    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();

    // Read i18n/messages_xxx.properties file.
    // For example: i18n/messages_en.properties
    messageSource.setBasename("classpath:i18n/messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setDefaultLocale(Locale.ENGLISH);
    return messageSource;
  }
}
