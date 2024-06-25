package com.sreview.sharedReview.domain.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secretKey}")
    private String secretKey;


    public String create(String email){
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 엑세스 토큰 유효시간 1시간
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .signWith(key,SignatureAlgorithm.HS256)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        return jwt;
    }

    public String validate(String jwt){ // JWT 토큰의 유효성 검사
        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try{
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt).getBody();
        }catch (ExpiredJwtException e) {
            throw e; // 예외를 던져서 상위 메서드에서 처리하도록 함
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }


    public String createRefreshToken(String email) {
        Date expiredDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS)); // 리프레시 토큰 유효시간 7일
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String refresh = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();

        return refresh;
    }

//    public String generateAccessToken(String jwt){ // JWT 토큰의 유효성 검사
//        Claims claims = null;
//        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//        try {
//            claims = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(jwt).getBody();
//            String email = claims.getSubject();
//
//            String newAccessToken = create(email);
//
//            return newAccessToken;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
