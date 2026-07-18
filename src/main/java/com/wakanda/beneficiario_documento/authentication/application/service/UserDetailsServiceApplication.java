package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.authentication.infra.AutenticacaoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserDetailsServiceApplication implements UserDetailsService {
    private final AutenticacaoRepository autenticacaoRepository;

    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        log.info("[inicio] UserDetailsServiceApplication - loadUserByUsername");
        UserDetails userDetails = autenticacaoRepository.encontrarPorEmail(username);
        log.info("[finaliza] UserDetailsServiceApplication - loadUserByUsername");
        return userDetails;
    }
}
