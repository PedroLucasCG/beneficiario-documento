package com.wakanda.beneficiario_documento.authentication.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/authentication")
public interface AutenticacaoAPI {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    void registrar(@RequestBody @Valid AutenticaticacaoRegistroRequest autenticaticacaoRegistroRequest);

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    AutenticacaoResponse login(@RequestBody @Valid AutenticacaoRequest autenticaticaoRequest);
}
