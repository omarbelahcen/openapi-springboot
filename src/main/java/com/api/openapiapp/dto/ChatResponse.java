package com.api.openapiapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChatResponse {

    private List<Choice> choices;



}
