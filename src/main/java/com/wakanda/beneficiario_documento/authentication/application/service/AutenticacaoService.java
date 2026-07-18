package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoRequest;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoResponse;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService {
    AuthUser cadastrarUsuario(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest);

    AutenticacaoResponse login(AutenticacaoRequest autenticaticaoRequest);
}
