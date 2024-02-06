package com.web.framework.config.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.framework.service.JwtService;
import com.web.framework.vo.UserVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Integer extractUserId(String token) {
    	Claims claims = extractAllClaims(token);
    	return (Integer) claims.get("userId");
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private String generateToken(Map<String, Object> extraClaims, UserVo userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUname())
				.claim("userId", userDetails.getId())  
				.claim("email", userDetails.getEmail())  
				.claim("status", userDetails.getStatus()) 
				.claim("firstname", userDetails.getFirstName())  
				.claim("lastname", userDetails.getLastName()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24*7))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}
    @Override
    public String generateToken(UserVo userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserVo userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUname())) && !isTokenExpired(token);
    }

    

	

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
