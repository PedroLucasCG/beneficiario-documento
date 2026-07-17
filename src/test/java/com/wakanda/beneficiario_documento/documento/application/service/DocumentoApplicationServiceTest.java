package com.wakanda.beneficiario_documento.documento.application.service;

import com.wakanda.beneficiario_documento.DataHelper;
import com.wakanda.beneficiario_documento.beneficiario.application.service.BeneficiarioApplicationService;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoListResponse;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import com.wakanda.beneficiario_documento.documento.infra.DocumentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentoApplicationServiceTest {

    @InjectMocks
    private DocumentoApplicationService service;

    @Mock
    private DocumentoRepository documentoRepository;
    @Mock
    private BeneficiarioApplicationService beneficiarioApplicationService;

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

        when(beneficiarioApplicationService.buscarBeneficiarioPorId(any())).thenReturn(beneficiario);
        when(documentoRepository.retornarTodosDocumentoBeneficiario(beneficiario.getId())).thenReturn(documentos);

        List<DocumentoListResponse> documentoListResponses = service.retornarDocumentosBeneficiario(beneficiario.getId());

        verify(documentoRepository, times(1)).retornarTodosDocumentoBeneficiario(any());
        verify(beneficiarioApplicationService, times(1)).buscarBeneficiarioPorId(any());
        assertEquals(DocumentoListResponse.class, documentoListResponses.get(0).getClass());
        assertEquals(documentos.size(), documentoListResponses.size());
    }
}