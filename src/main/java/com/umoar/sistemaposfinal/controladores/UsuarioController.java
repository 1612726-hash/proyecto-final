package com.umoar.sistemaposfinal.controladores;

import com.umoar.sistemaposfinal.modelos.Usuario;
import com.umoar.sistemaposfinal.modelos.Venta;
import com.umoar.sistemaposfinal.servicios.UsuarioService;
import com.umoar.sistemaposfinal.servicios.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/facturas")
    public ResponseEntity<List<Venta>> listarFacturasGlobales(@RequestParam(required = false) String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            return ResponseEntity.ok(ventaService.listarTodas(tipo));
        }
        return ResponseEntity.ok(ventaService.listarTodas());
    }

    @GetMapping("/{id}/facturas")
    public ResponseEntity<List<Venta>> listarFacturasDeUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.listarPorUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
