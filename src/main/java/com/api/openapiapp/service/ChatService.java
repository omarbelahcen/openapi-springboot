package com.api.openapiapp.service;

import com.api.openapiapp.dto.ChatRequest;
import com.api.openapiapp.dto.ChatResponse;
import com.api.openapiapp.repository.ProductRepository;
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

    private ProductRepository productRepository;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }
    public ChatResponse sendChatRequest(String prompt) {
        ChatRequest request = new ChatRequest(model, prompt);

        log.info("#### Request : {} ####", request);

        ChatResponse response = restTemplate.postForObject(
                apiUrl,
                request,
                ChatResponse.class);

        log.info("#### Response : {} ####", response);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException("Error no response !!");
        }
        return response;
    }

}
