package com.wakanda.beneficiario_documento.documento.application.api;

import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.domain.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoSalvarRequest {
    @NotNull
    private TipoDocumento tipoDocumento;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public Documento converteParaDocumento() {
        return new Documento(this);
    }
}
