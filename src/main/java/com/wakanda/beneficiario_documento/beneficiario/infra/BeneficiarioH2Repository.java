package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeneficiarioH2Repository extends JpaRepository<Beneficiario, UUID> {
}
