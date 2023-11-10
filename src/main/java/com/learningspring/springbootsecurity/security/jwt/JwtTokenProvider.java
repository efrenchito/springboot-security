package com.learningspring.springbootsecurity.security.jwt;

import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-miliseconds}")
    private long jwtExpirationDate;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build(); //JwtParser
    }

    private Claims extractAllClaims(String token) {
        return getJwtParser().parseClaimsJws(token).getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //Get username from Jwt token
    /*public String getUsername(String token) {//Jws<Claims>
      Claims claims = getJwtParser().parseClaimsJws(token).getBody();
      String username = claims.getSubject();
      return username;
    }*/

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Validate Jwt token
    public boolean isTokenValid(String token) {
        try {
            getJwtParser().parse(token);
            return true;
            //final String username = extractUsername(token);
            //return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        } catch( io.jsonwebtoken.MalformedJwtException exc) {
            throw new RuntimeException("Invalid JWT Token");  //BadRequestException
        } catch(ExpiredJwtException exc) {
            throw new RuntimeException("Expired JWT Token");
        } catch(UnsupportedJwtException exc) {
            throw new RuntimeException("Unsupported JWT Token");
        } catch(IllegalArgumentException exc) {
            throw new RuntimeException("JWT claims string is empty");
        }
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        java.util.Date currentDate = new Date();  //currentDate.getTime()
        java.util.Date expirationDate = new Date( System.currentTimeMillis() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(getSecretKey())         //.signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return token;
    }

}
