package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;

public interface AutenticacaoService {
    AuthUser cadastrarUsuario(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest);

}
