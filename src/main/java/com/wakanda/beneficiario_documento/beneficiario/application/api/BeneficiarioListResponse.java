package com.wakanda.beneficiario_documento.beneficiario.application.api;

import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioListResponse {
    private UUID id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;

    public static List<BeneficiarioListResponse> converter(List<Beneficiario> beneficiarios) {
        return null;
    }
}
