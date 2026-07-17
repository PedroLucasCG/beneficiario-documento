package com.wakanda.beneficiario_documento.beneficiario.infra;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BeneficiarioH2Repository extends JpaRepository<Beneficiario, UUID> {
    @Query("""
    SELECT b
    FROM Beneficiario b
    JOIN FETCH b.documentos
    """)
    List<Beneficiario> findAllBeneficiarioWithDocuments();

    Beneficiario findByEmail(String email);
}
