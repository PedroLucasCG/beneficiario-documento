package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Beneficiario buscarBeneficiarioPorId(UUID id) {
        log.info("[inicio] BeneficarioInfraRepository - buscarBeneficiarioPorId");
        Beneficiario beneficiario = beneficiarioH2Repository.findById(id).orElseThrow(() ->
                APIException.build(HttpStatus.NOT_FOUND, "Beneficiario informado não encontrado"));;
        log.info("[finaliza] BeneficarioInfraRepository - buscarBeneficiarioPorId");
        return beneficiario;
    }

    @Override
    public List<Beneficiario> retornarTodosBeneficiarios() {
        log.info("[inicio] BeneficarioInfraRepository - retornarTodosBeneficiarios");
        List<Beneficiario> beneficiarios = beneficiarioH2Repository.findAllBeneficiarioWithDocuments();
        log.info("[finaliza] BeneficarioInfraRepository - retornarTodosBeneficiarios");
        return beneficiarios;
    }

    @Override
    public void deletarBeneficiario(Beneficiario beneficiario) {
        log.info("[inicio] BeneficarioInfraRepository - deletarBeneficiario");
        beneficiarioH2Repository.delete(beneficiario);
        log.info("[finaliza] BeneficarioInfraRepository - deletarBeneficiario");
    }
}
