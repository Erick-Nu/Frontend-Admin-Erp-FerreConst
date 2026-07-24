package com.esnt.ferreconst.dto.request.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCompanyStatusRequestDto {

    @JsonProperty("emestado")
    private String status;

    public UpdateCompanyStatusRequestDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}