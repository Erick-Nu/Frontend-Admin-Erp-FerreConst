package com.esnt.ferreconst.model;

public class Auth {
    private String rut;
    private String user;
    private String password;

    public Auth() {
    }

    public Auth(String rut, String user, String password) {
        this.rut = rut;
        this.user = user;
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
