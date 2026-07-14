package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.domain.Documento;

import java.util.List;

public interface BeneficarioRepository {
    Beneficiario salvarBeneficiarioComDocumentosOpcionalmente(Beneficiario beneficiario, List<Documento> documentos);
}
