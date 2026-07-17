package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioAtualizarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficiarioH2Repository;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.application.service.DocumentoService;
import com.wakanda.beneficiario_documento.handler.APIException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeneficiarioApplicationService implements BeneficiarioService {
    private final BeneficarioRepository beneficarioRepository;
    private final DocumentoService documentoService;
    private final BeneficiarioH2Repository beneficiarioH2Repository;

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
        log.info("[inicio] BeneficiarioApplicationService - retornarTodosBeneficiarios");
        List<Beneficiario> beneficiarios = beneficarioRepository.retornarTodosBeneficiarios();
        List<BeneficiarioListResponse> beneficiarioListResponse = BeneficiarioListResponse.converter(beneficiarios);
        log.info("[finaliza] BeneficiarioApplicationService - retornarTodosBeneficiarios");
        return beneficiarioListResponse;
    }

    @Override
    public Beneficiario atualizarBeneficiario(BeneficiarioAtualizarRequest beneficiarioAtualizarRequest, UUID idBeneficiario) {
        log.info("[inicio] BeneficiarioApplicationService - atualizarBeneficiario");
        Beneficiario beneficiario = beneficarioRepository.buscarBeneficiarioPorId(idBeneficiario);
        beneficiario.atualizar(beneficiarioAtualizarRequest);
        Beneficiario beneficiarioAtualizado = beneficarioRepository.salvarBeneficiario(beneficiario);
        log.info("[finaliza] BeneficiarioApplicationService - atualizarBeneficiario");
        return beneficiarioAtualizado;
    }

    @Override
    public Beneficiario deleteBeneficiario(UUID idBeneficiario) {
        log.info("[inicio] BeneficiarioApplicationService - deleteBeneficiario");
        Beneficiario beneficiario = beneficarioRepository.buscarBeneficiarioPorId(idBeneficiario);
        beneficarioRepository.deletarBeneficiario(beneficiario);
        log.info("[finaliza] BeneficiarioApplicationService - deleteBeneficiario");
        return beneficiario;
    }
}
