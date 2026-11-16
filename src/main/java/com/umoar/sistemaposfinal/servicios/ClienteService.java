package com.umoar.sistemaposfinal.servicios;

import com.umoar.sistemaposfinal.modelos.Cliente;
import com.umoar.sistemaposfinal.repositorios.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerPorDocumento(String documento) {
        return clienteRepository.findByDocumento(documento);
    }

    @Transactional
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(existente -> {
            existente.setNombre(clienteActualizado.getNombre());
            existente.setApellido(clienteActualizado.getApellido());
            existente.setDocumento(clienteActualizado.getDocumento());
            existente.setTelefono(clienteActualizado.getTelefono());
            existente.setEmail(clienteActualizado.getEmail());
            return clienteRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
