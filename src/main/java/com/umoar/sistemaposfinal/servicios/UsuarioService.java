package com.umoar.sistemaposfinal.servicios;

import com.umoar.sistemaposfinal.modelos.Usuario;
import com.umoar.sistemaposfinal.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().startsWith("$2a$") && !usuario.getPassword().startsWith("$2b$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(existente -> {
            existente.setUsername(usuarioActualizado.getUsername());
            existente.setActivo(usuarioActualizado.isActivo());
            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isBlank()) {
                if (!usuarioActualizado.getPassword().startsWith("$2a$") && !usuarioActualizado.getPassword().startsWith("$2b$")) {
                    existente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                } else {
                    existente.setPassword(usuarioActualizado.getPassword());
                }
            }
            if (usuarioActualizado.getRoles() != null) {
                existente.setRoles(usuarioActualizado.getRoles());
            }
            return usuarioRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
