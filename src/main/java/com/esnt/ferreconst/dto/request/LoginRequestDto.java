package com.esnt.ferreconst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestDto {

    @JsonProperty("emruc")
    private String emruc;

    @JsonProperty("usapodo")
    private String usapodo;

    @JsonProperty("uspassword")
    private String uspassword;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String emruc, String usapodo, String uspassword) {
        this.emruc = emruc;
        this.usapodo = usapodo;
        this.uspassword = uspassword;
    }

    public String getEmruc() {
        return emruc;
    }

    public void setEmruc(String emruc) {
        this.emruc = emruc;
    }

    public String getUsapodo() {
        return usapodo;
    }

    public void setUsapodo(String usapodo) {
        this.usapodo = usapodo;
    }

    public String getUspassword() {
        return uspassword;
    }

    public void setUspassword(String uspassword) {
        this.uspassword = uspassword;
    }
}
