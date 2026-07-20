package com.esnt.ferreconst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshRequestDto {

    @JsonProperty("refreshToken")
    private String refreshToken;

    public RefreshRequestDto() {
    }

    public RefreshRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
