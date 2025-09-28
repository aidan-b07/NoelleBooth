package me.aidanbooth.noellebooth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import static me.aidanbooth.noellebooth.security.jwt.JwtShared.getSecret;

@Component
public class JwtClient {

    public Claims validateToken(String token) {
        return Jwts.parser().verifyWith(getSecret()).build().parseSignedClaims(token).getPayload();
    }
}
