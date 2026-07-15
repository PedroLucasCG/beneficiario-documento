package com.wakanda.beneficiario_documento.documento.application.api;

import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.domain.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoSalvoResponse {
    private UUID id;
    private TipoDocumento tipoDocumento;
    private String nome;
    private String descricao;

    public DocumentoSalvoResponse(Documento documento) {
        this.id = documento.getId();
        this.tipoDocumento = documento.getTipoDocumento();
        this.nome = documento.getNome();
        this.descricao = documento.getDescricao();
    }

    public static List<DocumentoSalvoResponse> converte(List<Documento> documentos) {
        if (documentos == null || documentos.isEmpty()) {
            return Collections.emptyList();
        }
        return documentos.stream()
                .map(DocumentoSalvoResponse::new)
                .collect(Collectors.toList());
    }
}
