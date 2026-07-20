package com.esnt.ferreconst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto {

    @JsonProperty("message")
    private String message;

    public ErrorResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
