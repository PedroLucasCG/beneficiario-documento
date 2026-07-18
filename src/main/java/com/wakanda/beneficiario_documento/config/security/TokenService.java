package com.wakanda.beneficiario_documento.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenService {
    @Value("${security.token.jwt.secret}")
    private String secret;

    @Value("${security.token.jwt.expiration}")
    private Long expiration;

    public String generateTokenUser(AuthUser authUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("pedrolcg")
                    .withSubject(authUser.getEmail())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar token: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("pedrolcg")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            log.error("Erro ao validar token: {}",  exception.getMessage());
            return null;
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        if(token == null)
            throw APIException.build(HttpStatus.BAD_REQUEST, "Token nulo!");

        return validateToken(token.replace("Bearer ", ""));
    }

}
