package com.nj.todolist.config;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtHelper {

    private static final long EXPIRATION_TIME = 86400000;
    @Value("${jwt.secret}")
    private String SECRET;

    public String createToken(String userName) {
        return JWT.create()
                .withSubject(userName)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

    public String verifyToken(String token) {
        return JWT.require(HMAC512(SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
