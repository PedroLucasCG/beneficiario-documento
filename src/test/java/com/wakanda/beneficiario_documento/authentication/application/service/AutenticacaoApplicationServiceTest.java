package com.wakanda.beneficiario_documento.authentication.application.service;

import com.wakanda.beneficiario_documento.DataHelper;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoRequest;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticacaoResponse;
import com.wakanda.beneficiario_documento.authentication.application.api.AutenticaticacaoRegistroRequest;
import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import com.wakanda.beneficiario_documento.authentication.infra.AutenticacaoRepository;
import com.wakanda.beneficiario_documento.config.security.TokenService;
import com.wakanda.beneficiario_documento.handler.APIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoApplicationServiceTest {
    @InjectMocks
    private AutenticacaoApplicationService service;

    @Mock
    private AutenticacaoRepository autenticacaoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "expiration", 8L);
    }

    @Test
    void registrarUsuarioComSucesso() {
        AutenticaticacaoRegistroRequest autenticacaoRegistroRequest = DataHelper.getAutenticaticacaoRegistroRequest();
        AuthUser authUser = DataHelper.getAuthUser();
        String encodedPassword = DataHelper.getEncodedPassword();

        when(autenticacaoRepository.salvarUsuario(any())).thenReturn(authUser);
        when(passwordEncoder.encode(any())).thenReturn(encodedPassword);

        AuthUser authUserSalvo = service.cadastrarUsuario(autenticacaoRegistroRequest);

        verify(autenticacaoRepository, times(1)).salvarUsuario(any());
        assertEquals(authUserSalvo.getEmail(), authUser.getEmail());
        assertNotEquals(authUserSalvo.getSenha(), autenticacaoRegistroRequest.getSenha());
    }

    @Test
    void fazerLoginComSucesso() {
        String token = DataHelper.getToken();
        Authentication authentication = DataHelper.getAuthentication();
        AutenticacaoRequest autenticacaoRequest = DataHelper.getAutenticacaoRequest();

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(tokenService.generateTokenUser(any())).thenReturn(token);

        AutenticacaoResponse autenticacaoResponse = service.login(autenticacaoRequest);

        verify(authenticationManager, times(1)).authenticate(any());
        verify(tokenService, times(1)).generateTokenUser(any());
        assertEquals(autenticacaoResponse.getToken(), token);
        long expiration = 8L;
        assertEquals(
                autenticacaoResponse.getExpiracao().getHour(),
                LocalDateTime.now().plusHours(expiration).getHour()
        );
    }

    @Test
    void fazerLoginComFalha() {
        AutenticacaoRequest autenticacaoRequest = DataHelper.getAutenticacaoRequest();

        doThrow(APIException.build(HttpStatus.FORBIDDEN,
                "Erro ao autenticar o usuario com e-mail " + autenticacaoRequest.getEmail()))
                .when(authenticationManager).authenticate(any());

        var exception = assertThrows(APIException.class, () -> {
            service.login(autenticacaoRequest);
        });
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusException());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(tokenService, times(0)).generateTokenUser(any());
    }
}