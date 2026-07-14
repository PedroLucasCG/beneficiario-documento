package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeneficiarioApplicationService implements BeneficiarioService {
    private final BeneficarioRepository beneficarioRepository;

    @Override
    public BeneficiarioSalvoResponse salvarBeneficiario(BeneficiarioSalvarRequest beneficiarioSalvarRequest) {
        log.info("[inicio] BeneficiarioApplicationService - salvarBeneficiario");
        Beneficiario beneficiario = new Beneficiario(beneficiarioSalvarRequest);
        Beneficiario beneficiarioSalvo
                = beneficarioRepository.salvarBeneficiarioComDocumentosOpcionalmente(beneficiario);
        List<Documento> documentos = beneficiarioSalvarRequest.getDocumentosSalvarRequests().stream()
                        .map((documentoRequest -> new Documento(documentoRequest, beneficiarioSalvo)))
                        .collect(Collectors.toList());
        BeneficiarioSalvoResponse beneficiarioSalvoResponse = new BeneficiarioSalvoResponse(beneficiarioSalvo);
        log.info("[finaliza] BeneficiarioApplicationService - salvarBeneficiario");
        return beneficiarioSalvoResponse;
    }
}
