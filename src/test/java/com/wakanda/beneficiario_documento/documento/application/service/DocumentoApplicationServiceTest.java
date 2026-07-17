package com.wakanda.beneficiario_documento.documento.application.service;

import com.wakanda.beneficiario_documento.DataHelper;
import com.wakanda.beneficiario_documento.beneficiario.application.service.BeneficiarioApplicationService;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoListResponse;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.infra.DocumentoRepository;
import com.wakanda.beneficiario_documento.handler.APIException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentoApplicationServiceTest {

    @InjectMocks
    private DocumentoApplicationService service;

    @Mock
    private DocumentoRepository documentoRepository;
    @Mock
    private BeneficarioRepository beneficarioRepository;

    @Test
    void salvarDocumentoComSucesso() {
        Beneficiario beneficiario = DataHelper.createBeneficiario();
        List<DocumentoSalvarRequest> documentoSalvarRequests = DataHelper.getDocumentosSalvarRequest();
        Documento documento = DataHelper.getDocumento();
        List<Documento> documentos = DataHelper.getDocumentos();

        when(documentoRepository.salvarDocumentoParaBeneficiario(any())).thenReturn(documento);

        List<DocumentoSalvoResponse> documentoSalvoResponses
                = service.salvarDocumentos(documentoSalvarRequests, beneficiario);

        verify(documentoRepository, times(documentos.size())).salvarDocumentoParaBeneficiario(any());
        assertEquals(DocumentoSalvoResponse.class, documentoSalvoResponses.get(0).getClass());
        assertEquals(documentos.size(), documentoSalvoResponses.size());
    }

    @Test
    void buscarDocumentosPorIdBeneficiarioComSucesso() {
        Beneficiario beneficiario = DataHelper.createBeneficiario();
        List<Documento> documentos = DataHelper.getDocumentos();

        when(beneficarioRepository.buscarBeneficiarioPorId(any())).thenReturn(beneficiario);
        when(documentoRepository.retornarTodosDocumentoBeneficiario(beneficiario.getId())).thenReturn(documentos);

        List<DocumentoListResponse> documentoListResponses = service.retornarDocumentosBeneficiario(beneficiario.getId());

        verify(documentoRepository, times(1)).retornarTodosDocumentoBeneficiario(any());
        verify(beneficarioRepository, times(1)).buscarBeneficiarioPorId(any());
        assertEquals(DocumentoListResponse.class, documentoListResponses.get(0).getClass());
        assertEquals(documentos.size(), documentoListResponses.size());
    }

    @Test
    void buscarDocumentosPorIdBeneficiarioComFalhaNotFound() {
        Beneficiario beneficiario = DataHelper.createBeneficiario();

        doThrow(APIException.build(HttpStatus.NOT_FOUND, "Beneficiario informado não encontrado"))
                .when(beneficarioRepository).buscarBeneficiarioPorId(any());

        var exception = assertThrows(APIException.class, () -> {
            service.retornarDocumentosBeneficiario(beneficiario.getId());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusException());
        verify(documentoRepository, times(0)).retornarTodosDocumentoBeneficiario(any());
        verify(beneficarioRepository, times(1)).buscarBeneficiarioPorId(any());
    }
}