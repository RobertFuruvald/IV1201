package com.server.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for handling JWT (JSON Web Token) operations,
 * including token generation, extraction, and validation.
 * <p>
 * This class encapsulates JWT processing logic, using the JJWT library
 * to create, parse, and validate tokens. It is designed to support
 * authentication
 * and authorization processes in the application.
 * </p>
 */
@Component
public class JwtUtil {

    // @Value("${jwt.secret}")
    private String secretKey = "testkey";
    // @Value("${jwt.expiration}")
    private long expiration = 60 * 60 * 1000;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    /**
     * Generates a JWT token for the given user details.
     * 
     * @param userDetails The {@link CustomUserDetailsPrincipal} for which to
     *                    generate the token.
     * @return A JWT token string.
     */
    public String generateToken(CustomUserDetailsPrincipal userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * Extracts the JWT token from the request header.
     * 
     * @param request The HTTP request {@link HttpServletRequest} from which to
     *                extract the token.
     * @return The extracted JWT token, or null if no token is found.
     */
    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX))
            return bearerToken.substring(TOKEN_PREFIX.length());
        return null;
    }

    /**
     * Validates the given JWT token against the user details.
     * 
     * @param token       The JWT token to validate.
     * @param userDetails The {@link CustomUserDetailsPrincipal} against which to
     *                    validate the token.
     * @return true if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token, CustomUserDetailsPrincipal userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the given JWT token.
     * 
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Checks if the given JWT token is expired.
     * 
     * @param token The JWT token to check.
     * @return true if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Extracts all claims from the given JWT token.
     * 
     * @param token The JWT token from which to extract claims.
     * @return A {@link Claims} object containing all claims from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
