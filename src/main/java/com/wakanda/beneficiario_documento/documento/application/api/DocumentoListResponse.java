package com.wakanda.beneficiario_documento.documento.application.api;

import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.domain.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoListResponse {
    private UUID id;
    private TipoDocumento tipoDocumento;
    private String nome;
    private String descricao;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;

    public DocumentoListResponse(Documento documento) {
        this.id = documento.getId();
        this.tipoDocumento = documento.getTipoDocumento();
        this.nome = documento.getNome();
        this.descricao = documento.getDescricao();
        this.dataInclusao = documento.getDataInclusao();
        this.dataAtualizacao = documento.getDataAtualizacao();
    }

    public static List<DocumentoListResponse> converter(List<Documento> documentos) {
        return documentos.stream()
                .map(DocumentoListResponse::new)
                .toList();
    }
}
