package com.restapidemo.restdemo.Models.DL.ServiceImpl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
@Service
public class JWTService {
	@Value("${jwt.secret}")
	private String SECRET;

	private Key getSignedKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String email, String role) {
		System.out.println("generate token");
		Map<String, Object> cmap = new HashMap<String, Object>();
		cmap.put("usertype", role.toUpperCase());
		return Createtoken(cmap, email);

	}

	private String Createtoken(Map<String, Object> cmap, String email) {
		System.out.println("create token");
		return Jwts.builder().claims(cmap).subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
				.signWith(getSignedKey()).compact();

	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) getSignedKey()).build().parseSignedClaims(token).getPayload();

	}

	private <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);

	}
	private String extractRole(String token)
	{
		Claims claims = extractAllClaims(token);
		return claims.get("usertype",String.class);
		
	}
	
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractSubjectFromToken(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token) {
		System.out.println(token + " Validate token" );
		try {
			return !isTokenExpired(token);
		} catch (Exception E) {
			 System.out.println(E.getMessage());
			 return false;
		}
	}

}
