package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.application.service.DocumentoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeneficiarioApplicationService implements BeneficiarioService {
    private final BeneficarioRepository beneficarioRepository;
    private final DocumentoService documentoService;

    @Override
    @Transactional
    public BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSalvarRequest) {
        log.info("[inicio] BeneficiarioApplicationService - salvarBeneficiario");
        Beneficiario beneficiario = new Beneficiario(beneficiarioSalvarRequest);
        var beneficiarioSalvo = beneficarioRepository.salvarBeneficiario(beneficiario);
        List<DocumentoSalvoResponse> documentoSalvoResponses
                = documentoService.salvarDocumentos(beneficiarioSalvarRequest.getDocumentosSalvarRequests(), beneficiarioSalvo);
        BeneficiarioSalvoResponse beneficiarioSalvoResponse
                = new BeneficiarioSalvoResponse(beneficiarioSalvo, documentoSalvoResponses);
        log.info("[finaliza] BeneficiarioApplicationService - salvarBeneficiario");
        return beneficiarioSalvoResponse;
    }

    @Override
    public List<BeneficiarioListResponse> retornarTodosBeneficiarios() {

        return List.of();
    }

    private Beneficiario buscarBeneficiarioPorId(UUID id) {
        log.info("[inicio] BeneficiarioApplicationService - buscarBeneficiarioPorId");
        Beneficiario beneficiario = beneficarioRepository.buscarBeneficiarioPorId(id)
                .orElseThrow(IllegalArgumentException::new);
        log.info("[finaliza] BeneficiarioApplicationService - buscarBeneficiarioPorId");
        return beneficiario;
    }
}
