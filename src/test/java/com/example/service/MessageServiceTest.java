package com.example.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.processor.MessageProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = MessageService.class)
class MessageServiceTest {

  @Autowired
  MessageService messageService;

  @MockBean
  MessageProcessor messageProcessor;

  @Test
  void testProcessMessage() {
    when(messageProcessor.publishMessage(any())).thenReturn(Mono.empty());
    StepVerifier.create(messageService.processMessage(Flux.just("Test")))
        .expectNext("All Message processed")
        .verifyComplete();
  }
}