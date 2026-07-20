package com.esnt.ferreconst.dto.request.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateConfigurationRequestDto {

    @JsonProperty("cfemid")
    private String cfemid;

    @JsonProperty("cfclave")
    private String cfclave;

    @JsonProperty("cfvalor")
    private String cfvalor;

    public CreateConfigurationRequestDto() {
    }

    public String getCfemid() {
        return cfemid;
    }

    public void setCfemid(String cfemid) {
        this.cfemid = cfemid;
    }

    public String getCfclave() {
        return cfclave;
    }

    public void setCfclave(String cfclave) {
        this.cfclave = cfclave;
    }

    public String getCfvalor() {
        return cfvalor;
    }

    public void setCfvalor(String cfvalor) {
        this.cfvalor = cfvalor;
    }
}
