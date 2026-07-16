package com.wakanda.beneficiario_documento.documento.application.service;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoListResponse;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;

import java.util.List;
import java.util.UUID;

public interface DocumentoService {
    List<DocumentoSalvoResponse> salvarDocumentos
            (List<DocumentoSalvarRequest> documentoSalvarRequests, Beneficiario beneficiario);

    List<DocumentoListResponse> retornarDocumentosBeneficiario(UUID idBeneficiario);
}
