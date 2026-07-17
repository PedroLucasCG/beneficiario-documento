package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioAtualizarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;

import java.util.List;
import java.util.UUID;

public interface BeneficiarioService {
    BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSAlvarRequest);

    List<BeneficiarioListResponse> retornarTodosBeneficiarios();

    Beneficiario atualizarBeneficiario(BeneficiarioAtualizarRequest beneficiarioAtualizarRequest, UUID idBeneficiario);

    Beneficiario deleteBeneficiario(UUID idBeneficiario);
}
