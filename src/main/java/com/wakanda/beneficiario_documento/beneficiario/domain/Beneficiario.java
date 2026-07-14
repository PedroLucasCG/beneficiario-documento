package com.wakanda.beneficiario_documento.beneficiario.domain;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    private String telefone;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @Column(nullable = false)
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE, mappedBy="beneficiario")
    private Set<Documento> documentos;

    public Beneficiario(BeneficiarioSalvarRequest beneficiarioSalvarRequest) {
        this.id = UUID.randomUUID();
        this.nome = beneficiarioSalvarRequest.getNome();
        this.telefone = beneficiarioSalvarRequest.getTelefone();
        this.dataNascimento = beneficiarioSalvarRequest.getDataNascimento();
        this.dataInclusao = LocalDate.now();
    }
}
