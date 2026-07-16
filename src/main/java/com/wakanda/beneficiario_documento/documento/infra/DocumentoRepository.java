package com.wakanda.beneficiario_documento.documento.infra;

import com.wakanda.beneficiario_documento.documento.domain.Documento;

import java.util.List;
import java.util.UUID;

public interface DocumentoRepository {
    Documento salvarDocumentoParaBeneficiario(Documento documento);

    List<Documento> retornarTodosDocumentoBeneficiario(UUID idBeneficiario);
}
