package com.example.sevkiyatbackend.login_register.user.token;

import com.example.sevkiyatbackend.login_register.user.dto.Credentials;
import com.example.sevkiyatbackend.login_register.user.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@ConditionalOnProperty(name = "hoaxify.token-type",havingValue = "jwt")
public class JwtTokenService implements TokenService{
    SecretKey key = Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public Token createToken(User user, Credentials creds) {
        TokenSubject tokenSubject = new TokenSubject(user.getId(),user.isActive());
        try {
            String subject = mapper.writeValueAsString(tokenSubject);
            String token = Jwts.builder().setSubject(subject).signWith(key).compact();
            return new Token("Bearer",token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if(authorizationHeader == null) return null;
        var token = authorizationHeader.split(" ")[1];
        JwtParser parser = Jwts.parser().setSigningKey(key).build();
        try {
            Jws<Claims> claims = parser.parseClaimsJws(token);
            var subject = claims.getBody().getSubject();
            var tokenSub = mapper.readValue(subject,TokenSubject.class);
            User user = new User();
            user.setId(tokenSub.id);
            user.setActive(tokenSub.active);
            return user;
        } catch (JwtException | JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
    public static record TokenSubject(long id , boolean active){
    }
    @Override
    public void logout(String authorizationHeader) {
    }
}
