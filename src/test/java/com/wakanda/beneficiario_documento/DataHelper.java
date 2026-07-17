package com.wakanda.beneficiario_documento;

import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.domain.Documento;

import java.util.List;
import java.util.UUID;

public class DataHelper {
    private static final UUID beneficiario1 = UUID.fromString("a713162f-20a9-4db9-a85b-90cd51ab18f4");

    public static List<DocumentoSalvarRequest> getDocumentosSalvarRequest() {
        return DocumentoSalvarRequest.converte(getDocumentos());
    }

    public static Beneficiario createBeneficiario() {
        return Beneficiario.builder().id(beneficiario1).nome("Pedro Lucas").documentos(getDocumentos()).build();
    }

    public static BeneficiarioSalvarRequest getBeneficiarioSalvarRequest() {
        return BeneficiarioSalvarRequest.builder().nome("Pedro Lucas").build();
    }

    public static List<Documento> getDocumentos() {
        return List.of(
                Documento.builder().id(UUID.randomUUID()).nome("Identidade").build(),
                Documento.builder().id(UUID.randomUUID()).nome("CPF").build(),
                Documento.builder().id(UUID.randomUUID()).nome("Certidão").build()
        );
    }

    public static Documento getDocumento() {
        return Documento.builder().nome("RG").build();
    }

    public static List<Beneficiario> getBeneficiarios() {
        return List.of(
                createBeneficiario(),
                createBeneficiario(),
                createBeneficiario()
        );
    }

    public static List<DocumentoSalvoResponse> getDocumentosSalvoResponse() {
        return DocumentoSalvoResponse.converte(getDocumentos());
    }

    public static BeneficiarioSalvoResponse getBeneficiarioSalvoResponse() {
        return new  BeneficiarioSalvoResponse(createBeneficiario(), getDocumentosSalvoResponse());
    }

    public static BeneficiarioListResponse getBeneficiarioListResponse() {
        return new BeneficiarioListResponse(createBeneficiario());
    }
}
