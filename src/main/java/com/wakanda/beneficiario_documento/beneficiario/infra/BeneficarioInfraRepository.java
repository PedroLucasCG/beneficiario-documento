package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class BeneficarioInfraRepository implements BeneficarioRepository {
    private final BeneficiarioH2Repository beneficiarioH2Repository;

    @Override
    public Beneficiario salvarBeneficiarioComDocumentosOpcionalmente(Beneficiario beneficiario, List<Documento> documentos) {
        log.info("[inicio] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");
        Beneficiario beneficiarioSalvo = beneficiarioH2Repository.save(beneficiario);
        log.info("[finaliza] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");
        return null;
    }
}
