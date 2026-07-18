package com.wakanda.beneficiario_documento.beneficiario.domain;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioAtualizarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
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

    @OneToMany(mappedBy="beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;

    public Beneficiario(BeneficiarioSalvarRequest beneficiarioSalvarRequest) {
        this.nome = beneficiarioSalvarRequest.getNome();
        this.telefone = beneficiarioSalvarRequest.getTelefone();
        this.dataNascimento = beneficiarioSalvarRequest.getDataNascimento();
        this.dataInclusao = LocalDate.now();
    }

    public void atualizar(BeneficiarioAtualizarRequest beneficiarioAtualizarRequest) {
        this.nome = beneficiarioAtualizarRequest.getNome();
        this.telefone = beneficiarioAtualizarRequest.getTelefone();
        this.dataNascimento = beneficiarioAtualizarRequest.getDataNascimento();
        this.dataAtualizacao = LocalDate.now();
    }
}
