package com.esnt.ferreconst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponseDto {

    @JsonProperty("message")
    private String message;

    public MessageResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}