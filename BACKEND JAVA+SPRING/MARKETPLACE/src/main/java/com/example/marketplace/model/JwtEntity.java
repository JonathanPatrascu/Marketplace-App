package com.example.marketplace.model;

import java.io.Serializable;

public class JwtEntity implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private  String jwtToken;
    public JwtEntity(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }


}
