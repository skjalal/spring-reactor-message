package com.example.processor;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
public class MessageProcessor {

  private final Sinks.Many<String> eventBus = Sinks.many().multicast().onBackpressureBuffer();

  public Mono<Void> publishMessage(Flux<String> messages) {
    return messages.map(eventBus::tryEmitNext).then();
  }

  public Flux<String> subscribeToMessages() {
    return eventBus.asFlux();
  }
}
