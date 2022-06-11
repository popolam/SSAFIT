package com.ssafy.ssafit.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	private static final String SALT = "SSAFIT";
	
	// 헤더 정보 입력
	// payload 입력한다.
	// 서명하고 compact 해서 문자열로 만들어준다.
	public String createToken(String claimId, String data) throws Exception {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
//				.setExpiration(new Date(System.currentTimeMillis() + 3000)) 3초
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
	}
	
	public void valid(String token) throws Exception {
		Jwts.parser()
			.setSigningKey(SALT.getBytes("UTF-8"))
			.parseClaimsJws(token);
	}
}