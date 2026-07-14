package com.wakanda.beneficiario_documento.documento.application.api;

import com.wakanda.beneficiario_documento.documento.domain.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoSalvoResponse {
    private UUID id;
    private TipoDocumento tipoDocumento;
    private String nome;
    private String descricao;
}
