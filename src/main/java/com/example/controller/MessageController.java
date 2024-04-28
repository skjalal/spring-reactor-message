package com.example.controller;

import com.example.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageController {

  private final MessageService messageService;

  @PostMapping(value = "/send", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
  public Mono<String> process(@RequestBody String message) {
    return messageService.processMessage(Flux.just(message.split(",")));
  }
}
