package com.ftn.studentservice.security.jwt;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.security.SecurityConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    //Secret used for encrypting and decrypting jwt token https://jwt.io/
    @Value("myXAuthSecret")
    private String jwtSecret;

    //Duration of JWT token validation
    @Value("600000000")
    private Long jwtExpirationMs;

    // Signature algorithm for signing JWT
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("wlqmpek")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("authorities", user.getAuthorities())
                // For ease of use only, totaly redundant - WLQ
                .claim("authority", user.getAuthorities().stream().findFirst())
                // For debugging purpose - WLQ
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("UCN", user.getUCN())
                .signWith(SIGNATURE_ALGORITHM, jwtSecret)
                .compact();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}
