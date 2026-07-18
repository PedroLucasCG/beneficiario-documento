package com.wakanda.beneficiario_documento.authentication.infra;

import com.wakanda.beneficiario_documento.authentication.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthUserH2Repository extends JpaRepository<AuthUser, UUID> {
}
