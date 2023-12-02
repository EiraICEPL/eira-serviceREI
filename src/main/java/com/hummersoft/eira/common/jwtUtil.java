package com.hummersoft.eira.common;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.hummersoft.eira.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtil {

    // Use Keys.secretKeyFor to generate a secure key for HS512
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long expiryDuration = 60 * 60;

    public String generateJwt(User user) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // Claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getUserId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // Generate JWT using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public void verify(String authorization) throws Exception {
        System.out.println(authorization);

        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parse(authorization);
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }

}
