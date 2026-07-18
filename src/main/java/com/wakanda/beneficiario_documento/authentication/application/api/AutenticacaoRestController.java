package com.wakanda.beneficiario_documento.authentication.application.api;

import com.wakanda.beneficiario_documento.authentication.application.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AutenticacaoRestController implements AutenticacaoAPI {
    private final AutenticacaoService autenticacaoService;

    @Override
    public void registrar(AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest) {
        log.info("[inicio] AutenticacaoRestController - registrar");
        autenticacaoService.cadastrarUsuario(autenticaticacaoRegistroRequest);
        log.info("[finaliza] AutenticacaoRestController - registrar");
    }

    @Override
    public AutenticacaoResponse login(AutenticacaoRequest autenticaticaoRequest) {
        log.info("[inicio] AutenticacaoRestController - login");

        log.info("[finaliza] AutenticacaoRestController - login");
        return null;
    }
}
