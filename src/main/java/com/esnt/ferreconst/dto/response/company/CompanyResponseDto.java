package com.esnt.ferreconst.dto.response.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class CompanyResponseDto {

    @JsonProperty("emid")
    private String id;

    @JsonProperty("emruc")
    private String ruc;

    @JsonProperty("emrznsocial")
    private String rznsocial;

    @JsonProperty("emcorreo")
    private String email;

    @JsonProperty("emlogo")
    private String logo;

    @JsonProperty("emcodigo")
    private String code;

    @JsonProperty("emfchregistro")
    private Instant dateRegister;

    @JsonProperty("emestado")
    private String status;

    @JsonProperty("empadre")
    private boolean isParent;

    public CompanyResponseDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRznsocial() {
        return rznsocial;
    }

    public void setRznsocial(String rznsocial) {
        this.rznsocial = rznsocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Instant dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }
}
