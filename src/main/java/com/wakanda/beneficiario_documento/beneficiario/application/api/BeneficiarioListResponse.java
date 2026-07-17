package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioListResponse {
    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private List<DocumentoListResponse> documentos;
    private LocalDate dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;

    public BeneficiarioListResponse(Beneficiario beneficiario) {
        this.id = beneficiario.getId();
        this.nome = beneficiario.getNome();
        this.telefone = beneficiario.getTelefone();
        this.email = beneficiario.getEmail();
        this.dataNascimento = beneficiario.getDataNascimento();
        this.dataInclusao = beneficiario.getDataInclusao();
        this.dataAtualizacao = beneficiario.getDataAtualizacao();
        this.documentos = DocumentoListResponse.converter(beneficiario.getDocumentos());
    }

    public static List<BeneficiarioListResponse> converter(List<Beneficiario> beneficiarios) {
        return beneficiarios.stream()
                .map(BeneficiarioListResponse::new)
                .toList();
    }
}
