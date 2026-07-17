package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.beneficiario.application.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class BeneficiarioRestController implements BeneficiarioAPI {
    private final BeneficiarioService beneficiarioService;

    @Override
    public BeneficiarioSalvoResponse salvarBeneficarioComDocumentos
            (BeneficiarioSalvarRequest beneficiarioSAlvarRequest) {
        log.info("[inicio] BeneficiarioRestController - salvarBeneficarioComDocumentos");
        BeneficiarioSalvoResponse beneficiarioSalvoResponse
                = beneficiarioService.salvarBeneficiario(beneficiarioSAlvarRequest);
        log.info("[finaliza] BeneficiarioRestController - salvarBeneficarioComDocumentos");
        return beneficiarioSalvoResponse;
    }

    @Override
    public List<BeneficiarioListResponse> retornarTodosBeneficiarios() {
        log.info("[inicio] BeneficiarioRestController - retornarTodosBeneficiarios");
        List<BeneficiarioListResponse> beneficiarioListResponse = beneficiarioService.retornarTodosBeneficiarios();
        log.info("[finaliza] BeneficiarioRestController - retornarTodosBeneficiarios");
        return beneficiarioListResponse;
    }

    @Override
    public void atualizarCadastroBeneficiario(BeneficiarioAtualizarRequest beneficiarioAtualizarRequest, UUID idBeneficiario) {
        log.info("[inicio] BeneficiarioRestController - atualizarCadastroBeneficiario");
        beneficiarioService.atualizarBeneficiario(beneficiarioAtualizarRequest, idBeneficiario);
        log.info("[finaliza] BeneficiarioRestController - atualizarCadastroBeneficiario");
    }

    @Override
    public void deletarBeneficiario(UUID idBeneficiario) {
        log.info("[inicio] BeneficiarioRestController - deletarBeneficiario");
        beneficiarioService.deleteBeneficiario(idBeneficiario);
        log.info("[finaliza] BeneficiarioRestController - deletarBeneficiario");
    }
}
