package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;

public interface BeneficiarioService {
    BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSAlvarRequest);
}
