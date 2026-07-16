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

    public DocumentoSalvarRequest(Documento documento) {
        this.tipoDocumento = documento.getTipoDocumento();
        this.nome = documento.getNome();
        this.descricao = documento.getDescricao();
    }

    public static List<DocumentoSalvarRequest> converte(List<Documento> documentos) {
        return documentos.stream()
                .map(DocumentoSalvarRequest::new)
                .toList();
    }
}
