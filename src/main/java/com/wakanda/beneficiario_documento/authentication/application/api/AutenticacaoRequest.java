package com.wakanda.beneficiario_documento.authentication.application.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoRequest {
    @NotBlank(message = "Email não pode ser nulo")
    @Schema(example = "999999999999@site.com.br")
    private String email;

    @NotBlank(message = "Senha não pode ser nula")
    @Schema(example = "SenhaSegura@123")
    private String senha;
}
