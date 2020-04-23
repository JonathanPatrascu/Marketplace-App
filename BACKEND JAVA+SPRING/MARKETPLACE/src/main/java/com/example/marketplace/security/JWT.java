package com.example.marketplace.security;

import com.example.marketplace.model.Utilizator;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {

    private final static String SECRET_KEY="jonathan";
    private final static long EXPIRATION_DATE=10*24*60*60*1000;


    public JWT() {
    }

    public String generateToken(Utilizator user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_DATE);

        return Jwts.builder()
                .setSubject(Long.toString(user.getIdUtilizator()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Long getUserIdFromJWT(String token) {
        if(!validateToken(token))
            return null;

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public Long findIdFromToken(String header)
    {
        String [] arrayStrings=header.split("\\s");
        String token=arrayStrings[1];
        return getUserIdFromJWT(token);
    }
}
