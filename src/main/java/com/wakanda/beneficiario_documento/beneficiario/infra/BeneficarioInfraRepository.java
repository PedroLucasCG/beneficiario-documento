package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class BeneficarioInfraRepository implements BeneficarioRepository {
    private final BeneficiarioH2Repository beneficiarioH2Repository;

    @Override
    public Beneficiario salvarBeneficiario(Beneficiario beneficiario) {
        log.info("[inicio] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");
        Beneficiario beneficiarioSalvo = beneficiarioH2Repository.save(beneficiario);
        log.info("[finaliza] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");
        return beneficiarioSalvo;
    }
}
