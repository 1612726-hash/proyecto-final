package com.umoar.sistemaposfinal.servicios;

import com.umoar.sistemaposfinal.modelos.Categoria;
import com.umoar.sistemaposfinal.modelos.Producto;
import com.umoar.sistemaposfinal.repositorios.ProductoRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Producto> listarPaginado(int page, int size, Long categoriaId, String search, String sortField, String sortDirection) {
        String safeField = switch (sortField != null ? sortField : "") {
            case "id", "idProducto" -> "idProducto";
            case "codigo", "codigoBarras" -> "codigo";
            case "nombre" -> "nombre";
            case "precio" -> "precio";
            case "existencia", "stock" -> "existencia";
            case "categoria" -> "categoria.nombre";
            default -> "idProducto";
        };

        Sort sort = "desc".equalsIgnoreCase(sortDirection)
                ? Sort.by(safeField).descending()
                : Sort.by(safeField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Producto> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoriaId != null) {
                predicates.add(cb.equal(root.get("categoria").get("id"), categoriaId));
            }

            if (search != null && !search.trim().isEmpty()) {
                String pattern = "%" + search.trim().toLowerCase() + "%";
                Predicate nombreLike = cb.like(cb.lower(root.get("nombre")), pattern);
                Predicate codigoLike = cb.like(cb.lower(root.get("codigo")), pattern);
                Join<Producto, Categoria> catJoin = root.join("categoria", JoinType.LEFT);
                Predicate catLike = cb.like(cb.lower(catJoin.get("nombre")), pattern);
                predicates.add(cb.or(nombreLike, codigoLike, catLike));
            }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };

        return productoRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Producto> obtenerPorCodigoBarras(String codigoBarras) {
        return productoRepository.findByCodigoBarras(codigoBarras);
    }

    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Transactional
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public Optional<Producto> actualizar(Long id, Producto productoActualizado) {
        return productoRepository.findById(id).map(existente -> {
            if (productoActualizado.getNombre() != null) {
                existente.setNombre(productoActualizado.getNombre());
            }
            if (productoActualizado.getCodigoBarras() != null && !productoActualizado.getCodigoBarras().isBlank()) {
                existente.setCodigoBarras(productoActualizado.getCodigoBarras());
            } else if (productoActualizado.getCodigo() != null && !productoActualizado.getCodigo().isBlank()) {
                existente.setCodigo(productoActualizado.getCodigo());
            }
            if (productoActualizado.getPrecio() != null) {
                existente.setPrecio(productoActualizado.getPrecio());
            }
            if (productoActualizado.getExistencia() != null) {
                existente.setExistencia(productoActualizado.getExistencia());
            } else if (productoActualizado.getStock() != null) {
                existente.setStock(productoActualizado.getStock());
            }
            if (productoActualizado.getCategoria() != null) {
                existente.setCategoria(productoActualizado.getCategoria());
            }
            return productoRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
