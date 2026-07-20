package com.esnt.ferreconst.dto.request.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCompanyRequestDto {

    @JsonProperty("emruc")
    private String emruc;

    @JsonProperty("emrznsocial")
    private String emrznsocial;

    @JsonProperty("emcorreo")
    private String emcorreo;

    @JsonProperty("emcodigo")
    private String emcodigo;

    public CreateCompanyRequestDto() {
    }

    public String getEmruc() {
        return emruc;
    }

    public void setEmruc(String emruc) {
        this.emruc = emruc;
    }

    public String getEmrznsocial() {
        return emrznsocial;
    }

    public void setEmrznsocial(String emrznsocial) {
        this.emrznsocial = emrznsocial;
    }

    public String getEmcorreo() {
        return emcorreo;
    }

    public void setEmcorreo(String emcorreo) {
        this.emcorreo = emcorreo;
    }

    public String getEmcodigo() {
        return emcodigo;
    }

    public void setEmcodigo(String emcodigo) {
        this.emcodigo = emcodigo;
    }
}
