package com.wakanda.beneficiario_documento.documento.domain;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="beneficiario_id", nullable=false)
    private Beneficiario beneficiario;

    public Documento(DocumentoSalvarRequest documentoSalvarRequest, Beneficiario beneficiario) {
        if (documentoSalvarRequest == null) {
            return;
        }

        this.tipoDocumento = documentoSalvarRequest.getTipoDocumento();
        this.nome = documentoSalvarRequest.getNome();
        this.descricao = documentoSalvarRequest.getDescricao();
        this.dataInclusao = LocalDate.now();
        this.beneficiario = beneficiario;
    }
}
