package com.example.service;

import com.example.processor.MessageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageService {

  private final MessageProcessor messageProcessor;

  public Mono<String> processMessage(Flux<String> messages) {
    return messageProcessor.publishMessage(messages).then(Mono.just("All Message processed"));
  }

}
