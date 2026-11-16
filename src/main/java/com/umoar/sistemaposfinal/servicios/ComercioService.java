package com.umoar.sistemaposfinal.servicios;

import com.umoar.sistemaposfinal.modelos.Comercio;
import com.umoar.sistemaposfinal.repositorios.ComercioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComercioService {

    private final ComercioRepository comercioRepository;

    @Transactional(readOnly = true)
    public List<Comercio> listarTodos() {
        return comercioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Comercio> obtenerPorId(Long id) {
        return comercioRepository.findById(id);
    }

    @Transactional
    public Comercio guardar(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    @Transactional
    public Optional<Comercio> actualizar(Long id, Comercio comercioActualizado) {
        return comercioRepository.findById(id).map(existente -> {
            existente.setNombre(comercioActualizado.getNombre());
            existente.setNit(comercioActualizado.getNit());
            existente.setDireccion(comercioActualizado.getDireccion());
            existente.setTelefono(comercioActualizado.getTelefono());
            return comercioRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (comercioRepository.existsById(id)) {
            comercioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
