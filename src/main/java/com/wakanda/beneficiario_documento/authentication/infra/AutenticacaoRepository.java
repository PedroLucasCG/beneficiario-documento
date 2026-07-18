package com.wakanda.beneficiario_documento.authentication.infra;

import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;

public interface AutenticacaoRepository {
    AuthUser salvarUsuario(AuthUser authUser);

    AuthUser encontrarPorEmail(String username);
}
