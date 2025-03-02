package com.agileApplication.backend.jwtConfig;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    //  Use Constructor-Based Initialization
    public JwtUtil() {
        String secretKey = "mySecretKeyAgileJWTSigning!12345"; //  //  At least 32 characters
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //  Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //  Extract Username from JWT Token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //  Validate jwt Token
    public boolean validateToken(String token, String expectedUsername) {
        try {
            System.out.println("validateToken method mei agyaa");
            String username = extractUsername(token);
            System.out.println("username extract hua");
            return username.equals(expectedUsername) && !isTokenExpired(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //  Check if Token is Expired
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }



}
