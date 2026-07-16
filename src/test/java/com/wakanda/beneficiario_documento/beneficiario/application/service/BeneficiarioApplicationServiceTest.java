package com.wakanda.beneficiario_documento.beneficiario.application.service;

import com.wakanda.beneficiario_documento.DataHelper;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioListResponse;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvarRequest;
import com.wakanda.beneficiario_documento.beneficiario.application.api.BeneficiarioSalvoResponse;
import com.wakanda.beneficiario_documento.beneficiario.domain.Beneficiario;
import com.wakanda.beneficiario_documento.beneficiario.infra.BeneficarioRepository;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvarRequest;
import com.wakanda.beneficiario_documento.documento.application.api.DocumentoSalvoResponse;
import com.wakanda.beneficiario_documento.documento.application.service.DocumentoService;
import com.wakanda.beneficiario_documento.documento.domain.Documento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficiarioApplicationServiceTest {
    @InjectMocks
    private BeneficiarioApplicationService service;

    @Mock
    private BeneficarioRepository beneficarioRepository;
    @Mock
    private DocumentoService documentoService;

    @Test
    void salvarBeneficiarioComDocumentoComSucesso() {
        Beneficiario beneficiario = DataHelper.createBeneficiario();
        List<DocumentoSalvoResponse>  documentoSalvoResponses = DataHelper.getDocumentosSalvoResponse();
        BeneficiarioSalvarRequest beneficiarioSalvarRequest = DataHelper.getBeneficiarioSalvarRequest();
        BeneficiarioSalvoResponse beneficiarioSalvoResponse = DataHelper.getBeneficiarioSalvoResponse();

        when(beneficarioRepository.salvarBeneficiario(any())).thenReturn(beneficiario);
        when(documentoService.salvarDocumentos(any(), any()))
                .thenReturn(documentoSalvoResponses);

        BeneficiarioSalvoResponse beneficiarioSalvoResponseRetornado = service.salvarBeneficiario(beneficiarioSalvarRequest);

        verify(beneficarioRepository, times(1)).salvarBeneficiario(any());
        verify(documentoService, times(1)).salvarDocumentos(any(), any());
        assertEquals(BeneficiarioSalvoResponse.class, beneficiarioSalvoResponseRetornado.getClass());
        assertEquals(beneficiarioSalvoResponse.getNome(), beneficiarioSalvoResponseRetornado.getNome());
        assertEquals(
                beneficiarioSalvoResponse.getDocumentos().size(),
                beneficiarioSalvoResponseRetornado.getDocumentos().size()
        );
    }

    @Test
    void salvarBeneficiarioSemDocumentoComSucesso() {
        Beneficiario beneficiario = DataHelper.createBeneficiario();
        BeneficiarioSalvarRequest beneficiarioSalvarRequest = DataHelper.getBeneficiarioSalvarRequest();
        BeneficiarioSalvoResponse beneficiarioSalvoResponse = DataHelper.getBeneficiarioSalvoResponse();

        when(beneficarioRepository.salvarBeneficiario(any())).thenReturn(beneficiario);
        when(documentoService.salvarDocumentos(any(), any()))
                .thenReturn(List.of());

        BeneficiarioSalvoResponse beneficiarioSalvoResponseRetornado = service.salvarBeneficiario(beneficiarioSalvarRequest);

        verify(beneficarioRepository, times(1)).salvarBeneficiario(any());
        verify(documentoService, times(1)).salvarDocumentos(any(), any());
        assertEquals(BeneficiarioSalvoResponse.class, beneficiarioSalvoResponseRetornado.getClass());
        assertEquals(beneficiarioSalvoResponse.getNome(), beneficiarioSalvoResponseRetornado.getNome());
        assertEquals(
                0,
                beneficiarioSalvoResponseRetornado.getDocumentos().size()
        );
    }

    @Test
    void retornarTodosBeneficiariosComSucesso() {
        List<Beneficiario> beneficiarios = DataHelper.getBeneficiarios();

        when(beneficarioRepository.retornarTodosBeneficiarios()).thenReturn(beneficiarios);

        List<BeneficiarioListResponse> beneficiarioListResponses = service.retornarTodosBeneficiarios();

        verify(beneficarioRepository, times(1)).retornarTodosBeneficiarios();
        assertEquals(BeneficiarioListResponse.class, beneficiarioListResponses.get(0).getClass());
    }

    @Test
    void retornarTodosBeneficiariosVaziosComSucesso() {
        when(beneficarioRepository.retornarTodosBeneficiarios()).thenReturn(List.of());

        List<BeneficiarioListResponse> beneficiarioListResponses = service.retornarTodosBeneficiarios();

        verify(beneficarioRepository, times(1)).retornarTodosBeneficiarios();
        assertEquals(0, beneficiarioListResponses.size());
    }
}