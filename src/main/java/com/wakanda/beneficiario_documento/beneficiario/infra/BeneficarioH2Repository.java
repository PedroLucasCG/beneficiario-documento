package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class BeneficarioH2Repository implements BeneficarioRepository {

    @Override
    public Beneficiario salvarBeneficiarioComDocumentosOpcionalmente(Beneficiario beneficiario, List<Documento> documentos) {
        log.info("[inicio] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");

        log.info("[finaliza] BeneficarioH2Repository - salvarBeneficiarioComDocumentosOpcionalmente");
        return null;
    }
}
