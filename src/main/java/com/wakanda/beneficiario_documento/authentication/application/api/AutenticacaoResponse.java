package com.wakanda.beneficiario_documento.authentication.application.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoResponse {
    private LocalDateTime expiracao;
    private String token;
}
