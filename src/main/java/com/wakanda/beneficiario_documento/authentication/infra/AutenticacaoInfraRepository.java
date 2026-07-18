package com.wakanda.beneficiario_documento.authentication.infra;

import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class AutenticacaoInfraRepository implements AutenticacaoRepository {
    private final AuthUserH2Repository authUserH2Repository;
    @Override
    public AuthUser salvarUsuario(AuthUser authUser) {
        log.info("[inicio] AutenticacaoInfraRepository - salvarUsuario");
        AuthUser authUserSalvo = authUserH2Repository.save(authUser);
        log.info("[finaliza] AutenticacaoInfraRepository - salvarUsuario");
        return authUserSalvo;
    }

    @Override
    public AuthUser encontrarPorEmail(String email) {
        log.info("[inicio] BeneficarioInfraRepository - encontrarPorEmail");
        AuthUser authUser = authUserH2Repository.findByEmail(email).orElseThrow(() ->
                APIException.build(HttpStatus.NOT_FOUND, "Email informando não pertence a um beneficiário"));
        log.info("[finaliza] BeneficarioInfraRepository - encontrarPorEmail");
        return authUser;
    }
}
