package com.example.JWT.util;

import com.example.JWT.model.AuthModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(long jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public Claims createAdminClaims(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType","admin");
        claims.put("accessToViewDetails","true");
        return Jwts.claims(claims);
    }
    public Claims createUserClaims(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType","public");
        claims.put("accessToViewDetails","false");
        return Jwts.claims(claims);
    }

    public String generateToken(AuthModel authModel) {
        System.out.println("Role " + authModel.getRole());
        Claims result = null;
        if(authModel.getRole() == null){
            return null;
        }
        else if(authModel.getRole().equals("user")){
            result = createUserClaims();
        }
        else if(authModel.getRole().equals("admin")) {
            result = createAdminClaims();
        }
    return Jwts.builder()
            .setClaims(result)
            .setSubject(String.valueOf(authModel.getEmail()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserRole(String token){
        Claims claims = extractClaims(token);
        System.out.println("Claims object " + claims.toString());
        return (String) claims.get("userType");
    }
}