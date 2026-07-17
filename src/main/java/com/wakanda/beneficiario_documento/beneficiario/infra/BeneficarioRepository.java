package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeneficarioRepository {
    Beneficiario salvarBeneficiario(Beneficiario beneficiario);

    Beneficiario buscarBeneficiarioPorId(UUID id);

    List<Beneficiario> retornarTodosBeneficiarios();

    void deletarBeneficiario(Beneficiario beneficiario);
}
