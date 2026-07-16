package com.wakanda.beneficiario_documento.documento.application.api;

import com.wakanda.beneficiario_documento.documento.application.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DocumentoRestController implements DocumentoAPI {
    private final DocumentoService documentoService;

    @Override
    public List<DocumentoListResponse> retornarDocumentosBeneficiario(UUID idBeneficiario) {
        log.info("[inicio] DocumentoRestController - retornarDocumentosBeneficiario");
        List<DocumentoListResponse> documentoListResponses
                = documentoService.retornarDocumentosBeneficiario(idBeneficiario);
        log.info("[finaliza] DocumentoRestController - retornarDocumentosBeneficiario");
        return documentoListResponses;
    }
}
