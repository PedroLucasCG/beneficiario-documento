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
    @NotNull
    private LocalDate dataNascimento;
    private List<DocumentoSalvarRequest> documentosSalvarRequests;
}
