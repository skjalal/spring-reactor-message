package com.example.listener;

import com.example.processor.MessageProcessor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Getter
@Component
public class MessageListener {

  private final Flux<String> receivedMessages;

  @Autowired
  public MessageListener(MessageProcessor messageProcessor) {
    this.receivedMessages = messageProcessor.subscribeToMessages();
  }

  @PostConstruct
  public void listen() {
    getReceivedMessages().subscribe(this::handleMessage);
  }

  private void handleMessage(String message) {
    log.info("Message received: {}", message);
  }
}
