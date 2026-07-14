package com.wakanda.beneficiario_documento.documento.infra;

import com.wakanda.beneficiario_documento.documento.domain.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class DocumentoInfraRepository implements DocumentoRepository {
    private final DocumentoH2Repository documentoH2Repository;

    @Override
    public Documento salvarDocumentoParaBeneficiario(Documento documento) {
        log.info("[inicio] DocumentoInfraRepository - salvarDocumentoParaBeneficiario");
        Documento documentoSalvo = documentoH2Repository.save(documento);
        log.info("[finaliza] DocumentoInfraRepository - salvarDocumentoParaBeneficiario");
        return documentoSalvo;
    }
}
