package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiarioSalvarRequest {
    @NotBlank(message = "O nome não deve ser vázio")
    private String nome;
    private String telefone;
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>\\[\\]\\\\/~`_+=;'\\-]).{8,}$",
            message = "A senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um caractere especial e ter no mínimo 8 caracteres"
    )
    @Schema(example = "SenhaSegura@123")
    private String senha;
    @NotNull
    private LocalDate dataNascimento;
    private List<DocumentoSalvarRequest> documentosSalvarRequests;

    public void atualizarParaSenhaSegura(@Nullable String senhaSegura) {
        this.senha = senhaSegura;
    }
}
