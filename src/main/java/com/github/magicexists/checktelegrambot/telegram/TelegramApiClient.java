package com.github.magicexists.checktelegrambot.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.Objects;


@Service
public class TelegramApiClient {

  private final String URL;
  private final String botToken;
  private final String groupId;
  private final RestTemplate restTemplate;

  public TelegramApiClient(
      @Value("${telegram.api-url}") String URL,
      @Value("${telegram.bot-token}") String botToken,
      @Value("${telegram.group-id}") String groupId
  ) {
    this.URL = URL;
    this.groupId = groupId;
    this.botToken = botToken;
    this.restTemplate = new RestTemplate();
  }

  public void sendImageWithCaption(String fileId, String caption) {

    ResponseEntity<Message> exchange = restTemplate.exchange(
        MessageFormat.format("{0}/bot{1}/sendPhoto?chat_id={2}&photo={3}&caption={4}",
            URL, botToken, groupId, fileId, caption), HttpMethod.GET, null, Message.class);
  }

  public File getDocumentFile(String fileId) {
    return restTemplate.execute(
        Objects.requireNonNull(getDocumentTelegramFileUrl(fileId)),
        HttpMethod.GET,
        null,
        clientHttpResponse -> {
          File ret = File.createTempFile("download", "tmp");
          StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
          return ret;
        });
  }

  private String getDocumentTelegramFileUrl(String fileId) {
    ResponseEntity<ApiResponse<org.telegram.telegrambots.meta.api.objects.File>> response = restTemplate.exchange(
        MessageFormat.format("{0}bot{1}/getFile?file_id={2}", URL, botToken, fileId),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<ApiResponse<org.telegram.telegrambots.meta.api.objects.File>>() {
        }
    );
    return Objects.requireNonNull(response.getBody()).getResult().getFileUrl(this.botToken);
  }
}