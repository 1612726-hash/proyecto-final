package com.umoar.sistemaposfinal.controladores;

import com.umoar.sistemaposfinal.modelos.Comercio;
import com.umoar.sistemaposfinal.servicios.ComercioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercios")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @GetMapping
    public ResponseEntity<List<Comercio>> listarTodos() {
        return ResponseEntity.ok(comercioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comercio> obtenerPorId(@PathVariable Long id) {
        return comercioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comercio> crear(@RequestBody Comercio comercio) {
        Comercio nuevo = comercioService.guardar(comercio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comercio> actualizar(@PathVariable Long id, @RequestBody Comercio comercio) {
        return comercioService.actualizar(id, comercio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (comercioService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
