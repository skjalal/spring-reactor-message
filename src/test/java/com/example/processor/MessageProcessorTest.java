package com.example.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(classes = MessageProcessor.class)
class MessageProcessorTest {

  @Autowired
  MessageProcessor processor;

  @Test
  void testPublishMessage() {
    StepVerifier.create(processor.publishMessage(Flux.just("A"))).verifyComplete();
  }

  @Test
  void testSubscribeToMessages() {
    StepVerifier.create(processor.subscribeToMessages()).expectNext("A").thenCancel().verify();
  }
}