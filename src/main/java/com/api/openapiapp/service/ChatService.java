package com.api.openapiapp.service;

import com.api.openapiapp.dto.ChatRequest;
import com.api.openapiapp.dto.ChatRequestDto;
import com.api.openapiapp.dto.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ChatService {

    @Qualifier("openaiRestTemplate")
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ChatResponse sendChatRequest(ChatRequestDto chatRequestDto) {

        ChatRequest chatRequest = new ChatRequest(model, chatRequestDto.getMessages());
        chatRequest.setModel(model);
        log.info("#### Request : {} ####", chatRequest);
        final ChatResponse response = restTemplate.postForObject(
                apiUrl,
                chatRequest,
                ChatResponse.class);
        log.info("#### Response : {} ####", response);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("Error no response !!");
        }
        return response;
    }

}
