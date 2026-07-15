package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioSalvoResponse {
    private UUID id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private List<DocumentoSalvoResponse> documentos;

    public BeneficiarioSalvoResponse(Beneficiario beneficiarioSalvo) {
        this.id = beneficiarioSalvo.getId();
        this.nome = beneficiarioSalvo.getNome();
        this.telefone = beneficiarioSalvo.getTelefone();
        this.dataNascimento = beneficiarioSalvo.getDataNascimento();
        this.documentos = DocumentoSalvoResponse.converte(beneficiarioSalvo.getDocumentos());
    }

    public BeneficiarioSalvoResponse
            (Beneficiario beneficiarioSalvo, List<DocumentoSalvoResponse> documentoSalvoResponses) {
        this.id = beneficiarioSalvo.getId();
        this.nome = beneficiarioSalvo.getNome();
        this.telefone = beneficiarioSalvo.getTelefone();
        this.dataNascimento = beneficiarioSalvo.getDataNascimento();
        this.documentos = documentoSalvoResponses;
    }
}
