package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BeneficiarioApplicationService implements BeneficiarioService {
    private final BeneficarioRepository beneficarioRepository;

    @Override
    public BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSAlvarRequest) {
        log.info("[inicio] BeneficiarioServiceImpl - salvarBeneficiario");

        log.info("[finaliza] BeneficiarioServiceImpl - salvarBeneficiario");
        return null;
    }
}
