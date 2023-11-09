package com.restapi.rizqnasionalwebsite.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders; 
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component; 

import java.security.Key; 
import java.util.Date; 

@Component
public class JwtService { 

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    // @Value("${jwt.expiration}")
    // private int jwtExpiration;

    public String generateTokenWithIdentityNumber(String identityNumber) {
        Date now = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .setSubject(identityNumber)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() { 
		byte[] keyBytes= Decoders.BASE64.decode(jwtSecret); 
		return Keys.hmacShaKeyFor(keyBytes); 
	} 

    public String getIdentityNumberFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                   .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
  }

    // public String extractIdentityNumber(String token) {
    //     return extractClaims(token, Claims::getSubject);
    // }

    // public boolean validateToken(String token) {
    //     return !isTokenExpired(token);
    // }

    // private Claims extractAllClaims(String token) { 
	// 	return Jwts 
	// 			.parserBuilder() 
	// 			.setSigningKey(getSignKey()) 
	// 			.build() 
	// 			.parseClaimsJws(token) 
	// 			.getBody(); 
	// } 

    // public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) { 
	// 	final Claims claims = extractAllClaims(token); 
	// 	return claimsResolver.apply(claims); 
	// } 

    // public Date extractExpiration(String token) { 
    //     return extractClaims(token, Claims::getExpiration); 
    // } 
	
	// private Boolean isTokenExpired(String token) { 
	// 	return extractExpiration(token).before(new Date()); 
	// } 

} 

