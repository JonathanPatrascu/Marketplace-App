package com.example.marketplace.model;

public class UserCredentials {
    private String username;
    private String parola;

    public UserCredentials(String username, String parola) {
        this.username = username;
        this.parola = parola;
    }

    public UserCredentials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}

