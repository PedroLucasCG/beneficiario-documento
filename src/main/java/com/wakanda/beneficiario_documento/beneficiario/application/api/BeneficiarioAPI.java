package com.wakanda.beneficiario_documento.beneficiario.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/beneficiario")
public interface BeneficiarioAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BeneficiarioSalvoResponse salvarBeneficarioComDocumentos(
            @RequestBody @Valid BeneficiarioSalvarRequest beneficiarioSAlvarRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<BeneficiarioListResponse> retornarTodosBeneficiarios();

    @PatchMapping("/{idBeneficiario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void atualizarCadastroBeneficiario(
            @RequestBody @Valid BeneficiarioAtualizarRequest beneficiarioAtualizarRequest,
            @PathVariable UUID idBeneficiario);

    @DeleteMapping("/{idBeneficiario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletarBeneficiario(@PathVariable UUID idBeneficiario);
}
