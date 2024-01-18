package com.api.openapiapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Choice {
    private int index;
    private Message message;
}
