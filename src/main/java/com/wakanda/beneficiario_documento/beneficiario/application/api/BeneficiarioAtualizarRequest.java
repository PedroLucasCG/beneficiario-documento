package com.wakanda.beneficiario_documento.beneficiario.application.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiarioAtualizarRequest {
    @NotBlank
    private String nome;
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
}
