package com.umoar.sistemaposfinal.controladores;

import com.umoar.sistemaposfinal.modelos.Producto;
import com.umoar.sistemaposfinal.servicios.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> listarTodos(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "idProducto") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {

        if (page != null || size != null) {
            int pageNumber = (page != null && page >= 0) ? page : 0;
            int pageSize = (size != null && size > 0) ? size : 10;
            Page<Producto> resultado = productoService.listarPaginado(
                    pageNumber, pageSize, categoriaId, search, sortField, sortDirection);
            return ResponseEntity.ok(resultado);
        }

        if (categoriaId != null) {
            return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
        }
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Producto>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "idProducto") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Page<Producto> resultado = productoService.listarPaginado(
                page, size, categoriaId, search, sortField, sortDirection);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigoBarras}")
    public ResponseEntity<Producto> obtenerPorCodigoBarras(@PathVariable String codigoBarras) {
        return productoService.obtenerPorCodigoBarras(codigoBarras)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevo = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
