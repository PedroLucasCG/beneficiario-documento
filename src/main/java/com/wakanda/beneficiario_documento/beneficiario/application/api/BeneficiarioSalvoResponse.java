package com.wakanda.beneficiario_documento.beneficiario.application.api;

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
}
