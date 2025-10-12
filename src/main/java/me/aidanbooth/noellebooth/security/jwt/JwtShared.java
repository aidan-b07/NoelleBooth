package me.aidanbooth.noellebooth.security.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JwtShared {
    public static SecretKey getSecret() {
        String raw = System.getenv("JWT_AUTH_SECRET");

        if(raw == null) {
            throw new IllegalArgumentException("JWT_AUTH_SECRET not set");
        }

        return Keys.hmacShaKeyFor(raw.getBytes(StandardCharsets.UTF_8));
    }
}
