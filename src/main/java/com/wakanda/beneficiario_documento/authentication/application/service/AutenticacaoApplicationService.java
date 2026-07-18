package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoRequest;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoResponse;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.authentication.infra.AutenticacaoRepository;
import com.wakanda.beneficiario_documento.config.security.TokenService;
import com.wakanda.beneficiario_documento.handler.APIException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class AutenticacaoApplicationService implements AutenticacaoService {
    private final AutenticacaoRepository autenticacaoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Value("${security.token.jwt.expiration}")
    private Long expiration;

    @Override
    public AuthUser cadastrarUsuario(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest) {
        log.info("[inicio] AutenticacaoApplicationService - cadastrarUsuario");
        processarSenhaSegura(autenticaticacaoRegistroRequest);
        AuthUser authUser = new AuthUser(autenticaticacaoRegistroRequest);
        AuthUser authUserSalvo = autenticacaoRepository.salvarUsuario(authUser);
        log.info("[finaliza] AutenticacaoApplicationService - cadastrarUsuario");
        return authUserSalvo;
    }

    @Override
    public AutenticacaoResponse login(AutenticacaoRequest autenticaticaoRequest) {
        log.info("[inicio] AutenticacaoApplicationService - login");
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                autenticaticaoRequest.getEmail().toLowerCase(),
                autenticaticaoRequest.getSenha()
        );
        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateTokenUser((AuthUser) Objects.requireNonNull(auth.getPrincipal()));
            AutenticacaoResponse autenticaoResponse
                    = new AutenticacaoResponse(LocalDateTime.now().plusHours(expiration), token);
            log.info("[finaliza] AutenticacaoApplicationService - login");
            return autenticaoResponse;
        } catch (Exception e) {
            log.error("[error] AutenticacaoApplicationService - login - {}", e.getMessage());
            throw APIException.build(
                    HttpStatus.FORBIDDEN,
                    "Erro ao autenticar o usuario com e-mail " + autenticaticaoRequest.getEmail()
            );
        }
    }

    private void processarSenhaSegura(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest) {
        log.info("[inicio] AutenticacaoApplicationService - gerarSenhaSegura");
        autenticaticacaoRegistroRequest
                .atualizarParaSenhaSegura(passwordEncoder.encode(autenticaticacaoRegistroRequest.getSenha()));
        log.info("[finaliza] AutenticacaoApplicationService - gerarSenhaSegura");
    }
}
