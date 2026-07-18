package com.wakanda.beneficiario_documento;

import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoRequest;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoResponse;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class DataHelper {
    private static final UUID beneficiario1 = UUID.fromString("a713162f-20a9-4db9-a85b-90cd51ab18f4");

    @Value("${security.token.jwt.expiration}")
    private static Long expiration;

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

    public static AuthUser getAuthUser() {
        return AuthUser.builder().id(UUID.randomUUID()).build();
    }

    public static AutenticaticacaoRegistroRequest getAutenticaticacaoRegistroRequest() {
        return new AutenticaticacaoRegistroRequest("pedro@pedro.com", "SenhaSegura@123");
    }

    public static String getEncodedPassword() {
        return "encodedPassword";
    }

    public static Authentication getAuthentication() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal())
                .thenReturn(getAuthUser());

        return authentication;
    }

    public static String getToken() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
    }

    public static AutenticacaoResponse getAutenticacaoResponse() {
        return new  AutenticacaoResponse(LocalDateTime.now().plusHours(expiration), getToken());
    }

    public static AutenticacaoRequest getAutenticacaoRequest() {
        return new AutenticacaoRequest(getAutenticaticacaoRegistroRequest().getEmail(), getAutenticaticacaoRegistroRequest().getSenha());
    }
}
