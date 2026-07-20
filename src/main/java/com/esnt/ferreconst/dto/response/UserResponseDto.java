package com.esnt.ferreconst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class UserResponseDto {

    @JsonProperty("usid")
    private String id;

    @JsonProperty("usemid")
    private String companyId;

    @JsonProperty("usnombre")
    private String name;

    @JsonProperty("usapodo")
    private String nickname;

    @JsonProperty("uscorreo")
    private String email;

    @JsonProperty("usimagen")
    private String image;

    @JsonProperty("usrol")
    private String role;

    @JsonProperty("usfchregistro")
    private Instant dateRegister;

    @JsonProperty("usestado")
    private String status;

    public UserResponseDto() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
