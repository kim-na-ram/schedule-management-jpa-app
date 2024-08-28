package com.bootcamp.schedulemanagementjpaapp.util;

import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.EXPIRE_ACCESS_TOKEN;

@Component
public class JWTUtil {
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    private final SecretKey secretKey;

    public JWTUtil(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createAccessToken(String email) {
        Date date = new Date();

        return Jwts.builder()
                .subject(email)
                .claim("email", email)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + TOKEN_TIME))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public String getUserEmail(String jwtToken) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken).getPayload().getSubject();
    }

    public void verifyToken(String jwtToken) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken);
        } catch (ExpiredJwtException e) {
            throw new ApiException(EXPIRE_ACCESS_TOKEN);
        }
    }
}
