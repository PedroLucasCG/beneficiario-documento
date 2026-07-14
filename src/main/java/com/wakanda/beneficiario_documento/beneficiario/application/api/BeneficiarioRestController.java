package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.beneficiario.application.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class BeneficiarioRestController implements BeneficiarioAPI {
    private final BeneficiarioService beneficiarioService;

    @Override
    public BeneficiarioSalvoResponse salvarBeneficarioComDocumentos
            (BeneficiarioSalvarRequest beneficiarioSAlvarRequest) {
        log.info("[inicio] BeneficiarioRestController - salvarBeneficarioComDocumentos");
        BeneficiarioSalvoResponse beneficiarioSalvoResponse
                = beneficiarioService.salvarBeneficiario(beneficiarioSAlvarRequest);
        log.info("[finaliza] BeneficiarioRestController - salvarBeneficarioComDocumentos");
        return beneficiarioSalvoResponse;
    }
}
