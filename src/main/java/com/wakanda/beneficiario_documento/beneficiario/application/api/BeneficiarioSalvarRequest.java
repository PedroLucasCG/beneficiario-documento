package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiarioSalvarRequest {
    @NotBlank
    private String nome;
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
    private List<DocumentoSalvarRequest> documentosSalvarRequests;
}
