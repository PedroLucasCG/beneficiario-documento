package com.wakanda.beneficiario_documento.authentication.application.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticaticacaoRegistroRequest {
    @NotBlank(message = "Email não pode ser nulo")
    @Schema(example = "999999999999@site.com.br")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>\\[\\]\\\\/~`_+=;'\\-]).{8,}$",
            message = "A senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um caractere especial e ter no mínimo 8 caracteres"
    )
    @Schema(example = "SenhaSegura@123")
    private String senha;

    public void atualizarParaSenhaSegura(@Nullable String senhaSegura) {
        this.senha = senhaSegura;
    }
}
