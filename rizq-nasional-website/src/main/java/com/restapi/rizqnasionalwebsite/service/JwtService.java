package com.restapi.rizqnasionalwebsite.service;

import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
import io.jsonwebtoken.io.Decoders; 
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component; 

import java.security.Key; 
import java.util.Date; 
import java.util.function.Function; 

@Component
public class JwtService { 

    @Value("${jwt.secret}")
    private String jwtSecret;

    // @Value("${jwt.expiration}")
    // private int jwtExpiration;

    public String generateTokenWithIdentityNumber(String identityNumber) {
        Date now = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
        // new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(identityNumber)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractIdentityNumber(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) { 
		return Jwts 
				.parserBuilder() 
				.setSigningKey(getSignKey()) 
				.build() 
				.parseClaimsJws(token) 
				.getBody(); 
	} 

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) { 
		final Claims claims = extractAllClaims(token); 
		return claimsResolver.apply(claims); 
	} 

    public Date extractExpiration(String token) { 
        return extractClaims(token, Claims::getExpiration); 
    } 

	private Key getSignKey() { 
		byte[] keyBytes= Decoders.BASE64.decode(jwtSecret); 
		return Keys.hmacShaKeyFor(keyBytes); 
	} 

	
	private Boolean isTokenExpired(String token) { 
		return extractExpiration(token).before(new Date()); 
	} 

} 

