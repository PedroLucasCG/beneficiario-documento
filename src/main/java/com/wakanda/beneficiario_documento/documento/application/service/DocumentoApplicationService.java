package com.wakanda.beneficiario_documento.documento.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.service.BeneficiarioService;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoListResponse;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.infra.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentoApplicationService implements DocumentoService {
    private final DocumentoRepository documentoRepository;
    private final BeneficiarioService beneficarioService;

    @Override
    public List<DocumentoSalvoResponse> salvarDocumentos
            (List<DocumentoSalvarRequest> documentosSalvarRequests, Beneficiario beneficiario) {
        log.info("[inicio] DocumentoApplicationService - salvarDocumento");
        List<Documento> documentos = documentosSalvarRequests.stream()
                .map((documentoRequest) -> {
                   Documento documento = new Documento(documentoRequest, beneficiario);
                   return documentoRepository.salvarDocumentoParaBeneficiario(documento);
                })
                .toList();
        List<DocumentoSalvoResponse> documentosSalvarResponses = DocumentoSalvoResponse.converte(documentos);
        log.info("[finaliza] DocumentoApplicationService - salvarDocumento");
        return documentosSalvarResponses;
    }

    @Override
    public List<DocumentoListResponse> retornarDocumentosBeneficiario(UUID idBeneficiario) {
        log.info("[inicio] DocumentoApplicationService - retornarDocumentosBeneficiario");
        beneficarioService.buscarBeneficiarioPorId(idBeneficiario);
        List<Documento> documentos = documentoRepository.retornarTodosDocumentoBeneficiario(idBeneficiario);
        List<DocumentoListResponse> documentoListResponses = DocumentoListResponse.converter(documentos);
        log.info("[finaliza] DocumentoApplicationService - retornarDocumentosBeneficiario");
        return documentoListResponses;
    }
}
