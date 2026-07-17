package com.wakanda.beneficiario_documento.documento.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/beneficiario/{idBeneficiario}/documento")
public interface DocumentoAPI {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<DocumentoListResponse> retornarDocumentosBeneficiario(@PathVariable UUID idBeneficiario);
}
