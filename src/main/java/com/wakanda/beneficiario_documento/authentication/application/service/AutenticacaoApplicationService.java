package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.authentication.infra.AutenticacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AutenticacaoApplicationService implements AutenticacaoService {
    private final AutenticacaoRepository autenticacaoRepository;

    @Override
    public AuthUser cadastrarUsuario(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest) {
        log.info("[inicio] AutenticacaoApplicationService - cadastrarUsuario");
        AuthUser authUser = new AuthUser(autenticaticacaoRegistroRequest);
        AuthUser authUserSalvo = autenticacaoRepository.salvarUsuario(authUser);
        log.info("[finaliza] AutenticacaoApplicationService - cadastrarUsuario");
        return authUserSalvo;
    }
}
