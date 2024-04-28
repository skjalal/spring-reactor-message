package com.example.listener;

import static org.mockito.Mockito.when;

import com.example.processor.MessageProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class MessageListenerTest {

  MessageListener messageListener;

  @Mock
  MessageProcessor messageProcessor;

  @BeforeEach
  void setUp() {
    when(messageProcessor.subscribeToMessages()).thenReturn(Flux.just("A", "B"));
    messageListener = new MessageListener(messageProcessor);
  }

  @Test
  void testListen() {
    messageListener.listen();
    StepVerifier.create(messageListener.getReceivedMessages())
        .expectNext("A")
        .expectNext("B")
        .verifyComplete();
  }
}