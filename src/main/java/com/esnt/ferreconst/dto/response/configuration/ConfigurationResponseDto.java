package com.esnt.ferreconst.dto.response.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationResponseDto {

    @JsonProperty("cfid")
    private String id;

    @JsonProperty("cfemid")
    private String companyId;

    @JsonProperty("cfclave")
    private String key;

    @JsonProperty("cfvalor")
    private String value;

    public ConfigurationResponseDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
