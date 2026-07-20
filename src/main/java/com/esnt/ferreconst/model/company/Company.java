package com.esnt.ferreconst.model.company;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Company {

    private String id;
    private String ruc;
    private String rznsocial;
    private String email;
    private String logo;
    private String code;
    private Instant dateRegister;
    private String status;
    private boolean isParent;

    public Company() {
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

    public String getFormattedDateRegister() {
        if (dateRegister == null) return null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yy")
                .withZone(ZoneId.systemDefault());
        return fmt.format(dateRegister);
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
