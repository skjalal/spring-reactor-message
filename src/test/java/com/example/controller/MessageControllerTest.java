package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(MessageController.class)
class MessageControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @MockBean
  MessageService messageService;

  @Test
  void testProcess() {
    when(messageService.processMessage(any())).thenReturn(Mono.just("Processed: Test"));
    webTestClient.post()
        .uri("/send")
        .bodyValue("Test")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo("Processed: Test");
  }
}