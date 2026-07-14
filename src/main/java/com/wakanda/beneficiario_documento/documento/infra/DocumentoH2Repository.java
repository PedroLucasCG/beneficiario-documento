package com.wakanda.beneficiario_documento.documento.infra;

import com.wakanda.beneficiario_documento.documento.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentoH2Repository extends JpaRepository<Documento, UUID> {
}
