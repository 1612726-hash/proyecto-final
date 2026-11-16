package com.umoar.sistemaposfinal.repositorios;

import com.umoar.sistemaposfinal.modelos.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);
    List<Venta> findByOrderByFechaVentaDesc();
    List<Venta> findByTipoIgnoreCaseOrderByFechaVentaDesc(String tipo);
    List<Venta> findByUsuarioIdOrderByFechaVentaDesc(Long usuarioId);
    List<Venta> findByUsuarioUsernameOrderByFechaVentaDesc(String username);
    boolean existsByNumeroFactura(String numeroFactura);
}
