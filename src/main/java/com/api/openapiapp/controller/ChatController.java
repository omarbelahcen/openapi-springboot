package com.api.openapiapp.controller;

import com.api.openapiapp.dto.ChatResponse;
import com.api.openapiapp.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    /**
     * Creates a chat request and sends it to the OpenAI API
     * Returns the first message from the API response
     *
     * @param prompt the prompt to send to the API
     * @return first message from the API response
     */
    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> sendChatRequestToOpenAI(@RequestBody String prompt) {
        ChatResponse response = chatService.sendChatRequest(prompt);
        log.info("Here is the response : " + response.getChoices().size());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
