package com.wakanda.beneficiario_documento.authentication.infra;

import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class AutenticacaoInfraRepository implements AutenticacaoRepository {
    private final AuthUserH2Repository authUserH2Repository;
    @Override
    public AuthUser salvarUsuario(AuthUser authUser) {
        log.info("[inicio] AutenticacaoInfraRepository - salvarUsuario");

        log.info("[finaliza] AutenticacaoInfraRepository - salvarUsuario");
        return null;
    }
}
