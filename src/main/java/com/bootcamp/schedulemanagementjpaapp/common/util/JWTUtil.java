package com.bootcamp.schedulemanagementjpaapp.common.util;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_AUTHORITY;
import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.EXPIRE_ACCESS_TOKEN;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.INVALID_ACCESS_TOKEN;

@Component
public class JWTUtil {
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    private final SecretKey secretKey;

    public JWTUtil(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createAccessToken(String email, String authority) {
        Date date = new Date();

        return Jwts.builder()
                .subject(email)
                .claim(USER_EMAIL, email)
                .claim(USER_AUTHORITY, authority)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + TOKEN_TIME))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    private Jws<Claims> getClaimsFromToken(String jwtToken) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken);
    }

    public String getUserEmail(String jwtToken) {
        return getClaimsFromToken(jwtToken).getPayload().getSubject();
    }

    public String getAuthority(String jwtToken) {
        return getClaimsFromToken(jwtToken).getPayload().get(USER_AUTHORITY).toString();
    }

    public void verifyToken(String jwtToken) {
        try {
            getClaimsFromToken(jwtToken);
        } catch (SignatureException e) {
            throw new ApiException(INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApiException(EXPIRE_ACCESS_TOKEN);
        }
    }
}
