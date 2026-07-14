package com.wakanda.beneficiario_documento.beneficiario.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/beneficiario")
public interface BeneficiarioAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BeneficiarioSalvoResponse salvarBeneficarioComDocumentos(
            @RequestBody @Valid BeneficiarioSalvarRequest beneficiarioSAlvarRequest);
}
