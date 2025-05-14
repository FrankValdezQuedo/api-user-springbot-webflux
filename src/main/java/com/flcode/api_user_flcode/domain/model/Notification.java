package com.flcode.api_user_flcode.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notification {

    private String category;
    private String code;
    private String message;
    private String description;
    private String action;
    private String metadata;
    private String uuid;
    private String timestamp;
    private String severity;
}
