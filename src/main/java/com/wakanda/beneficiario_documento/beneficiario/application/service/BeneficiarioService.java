package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;

import java.util.List;

public interface BeneficiarioService {
    BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSAlvarRequest);

    List<BeneficiarioListResponse> retornarTodosBeneficiarios();
}
