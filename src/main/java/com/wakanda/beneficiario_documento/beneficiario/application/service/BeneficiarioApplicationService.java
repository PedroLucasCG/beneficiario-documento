package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.documento.application.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeneficiarioApplicationService implements BeneficiarioService {
    private final BeneficarioRepository beneficarioRepository;
    private final DocumentoService documentoService;

    @Override
    public BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSalvarRequest) {
        log.info("[inicio] BeneficiarioApplicationService - salvarBeneficiario");
        Beneficiario beneficiario = new Beneficiario(beneficiarioSalvarRequest);
        Beneficiario beneficiarioSalvo
                = beneficarioRepository.salvarBeneficiarioComDocumentosOpcionalmente(beneficiario);
        documentoService.salvarDocumentos(beneficiarioSalvarRequest.getDocumentosSalvarRequests(), beneficiarioSalvo);
        BeneficiarioSalvoResponse beneficiarioSalvoResponse = new BeneficiarioSalvoResponse(beneficiarioSalvo);
        log.info("[finaliza] BeneficiarioApplicationService - salvarBeneficiario");
        return beneficiarioSalvoResponse;
    }
}
