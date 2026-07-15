package com.wakanda.beneficiario_documento.documento.application.service;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;

import java.util.List;

public interface DocumentoService {
    List<DocumentoSalvoResponse> salvarDocumentos
            (List<DocumentoSalvarRequest> documentoSalvarRequests, Beneficiario beneficiario);
}
