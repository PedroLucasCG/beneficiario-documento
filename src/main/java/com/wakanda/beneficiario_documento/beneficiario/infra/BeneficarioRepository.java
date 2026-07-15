package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;

public interface BeneficarioRepository {
    Beneficiario salvarBeneficiario(Beneficiario beneficiario);
}
