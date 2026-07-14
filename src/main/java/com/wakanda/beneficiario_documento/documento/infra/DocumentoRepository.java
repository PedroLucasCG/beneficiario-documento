package com.wakanda.beneficiario_documento.documento.infra;

import com.wakanda.beneficiario_documento.documento.domain.Documento;

public interface DocumentoRepository {
    Documento salvarDocumentoParaBeneficiario(Documento documento);
}
