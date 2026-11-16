package com.umoar.sistemaposfinal.controladores;

import com.umoar.sistemaposfinal.seguridad.dto.LoginRequest;
import com.umoar.sistemaposfinal.seguridad.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            Set<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            String basicToken = java.util.Base64.getEncoder().encodeToString(
                    (loginRequest.getUsername() + ":" + loginRequest.getPassword()).getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );

            LoginResponse response = LoginResponse.builder()
                    .success(true)
                    .mensaje("Inicio de sesión exitoso")
                    .username(authentication.getName())
                    .roles(roles)
                    .token("Basic " + basicToken)
                    .build();

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {
            LoginResponse response = LoginResponse.builder()
                    .success(false)
                    .mensaje("Credenciales inválidas: usuario o contraseña incorrectos")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (DisabledException ex) {
            LoginResponse response = LoginResponse.builder()
                    .success(false)
                    .mensaje("La cuenta de usuario está desactivada")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (AuthenticationException ex) {
            LoginResponse response = LoginResponse.builder()
                    .success(false)
                    .mensaje("Error de autenticación: " + ex.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
