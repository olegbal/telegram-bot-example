package com.github.magicexists.checktelegrambot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class TranslationConfig {

  @Bean(name = "localeResolver")
  public LocaleResolver getLocaleResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setCookieDomain("myAppLocaleCookie");
    // 60 minutes 
    resolver.setCookieMaxAge(60 * 60);
    return resolver;
  }

  @Bean(name = "messageSource")
  public MessageSource getMessageResource() {
    ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

    // Read i18n/messages_xxx.properties file.
    // For example: i18n/messages_en.properties
    messageResource.setBasename("classpath:i18n/messages");
    messageResource.setDefaultEncoding("UTF-8");
    return messageResource;
  }
}
