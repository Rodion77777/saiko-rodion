package microservice.module.eurekaclientuser.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final String JWT_SECRET = "secret";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("your_very_long_secret_key_for_hmac".getBytes());
    private static final long expirationTime = 1000 * 60 * 60;

    public static String generateDepToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.ES256, JWT_SECRET)
                .compact();
    }

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SECRET_KEY)
                .compact();
    }
}
