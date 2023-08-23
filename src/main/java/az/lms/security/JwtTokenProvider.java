package az.lms.security;

import az.lms.enums.TokenType;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${security.jwtSecret}")
    private String jwtSecret;

    @Value("${security.accessTokenExpirationInMs}")
    private String accessTokenExpirationInMs;

    @Value("${security.refreshTokenExpirationInMs}")
    private String refreshTokenExpirationInMs;

    public String generateToken(UserDetails userDetails, TokenType tokenType) {
        var expirationInMs = tokenType == TokenType.ACCESS_TOKEN ? accessTokenExpirationInMs : refreshTokenExpirationInMs;
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Integer.parseInt(expirationInMs));
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
