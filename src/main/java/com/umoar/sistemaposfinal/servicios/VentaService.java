package com.umoar.sistemaposfinal.servicios;

import com.umoar.sistemaposfinal.modelos.DetalleVenta;
import com.umoar.sistemaposfinal.modelos.Producto;
import com.umoar.sistemaposfinal.modelos.Venta;
import com.umoar.sistemaposfinal.repositorios.ProductoRepository;
import com.umoar.sistemaposfinal.repositorios.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Venta> listarTodas() {
        return ventaRepository.findByOrderByFechaVentaDesc();
    }

    @Transactional(readOnly = true)
    public List<Venta> listarTodas(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            return ventaRepository.findByTipoIgnoreCaseOrderByFechaVentaDesc(tipo.trim());
        }
        return ventaRepository.findByOrderByFechaVentaDesc();
    }

    @Transactional(readOnly = true)
    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Venta> listarPorCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }

    @Transactional(readOnly = true)
    public List<Venta> listarPorUsuario(Long usuarioId) {
        return ventaRepository.findByUsuarioIdOrderByFechaVentaDesc(usuarioId);
    }

    @Transactional
    public Venta registrarVenta(Venta venta) {
        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDateTime.now());
        }

        if (venta.getTipo() == null || venta.getTipo().trim().isEmpty()) {
            venta.setTipo("COMPRA");
        } else {
            venta.setTipo(venta.getTipo().trim().toUpperCase());
        }

        if (venta.getNumeroFactura() == null || venta.getNumeroFactura().trim().isEmpty()) {
            String prefijo = "RENTA".equalsIgnoreCase(venta.getTipo()) ? "REN-" : "FAC-";
            long count = ventaRepository.count() + 1;
            venta.setNumeroFactura(prefijo + String.format("%05d", count));
        }

        BigDecimal totalCalculado = BigDecimal.ZERO;

        if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                detalle.setVenta(venta);

                if (detalle.getProducto() != null && detalle.getProducto().getId() != null) {
                    Producto producto = productoRepository.findById(detalle.getProducto().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + detalle.getProducto().getId()));

                    // Validar y descontar stock
                    int cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 1;
                    if (producto.getStock() != null && producto.getStock() < cantidad) {
                        throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre() 
                                + " (Disponible: " + producto.getStock() + ", Solicitado: " + cantidad + ")");
                    }

                    if (producto.getStock() != null) {
                        producto.setStock(producto.getStock() - cantidad);
                        productoRepository.save(producto);
                    }

                    // Establecer precio si no viene definido
                    if (detalle.getPrecioUnitario() == null && producto.getPrecio() != null) {
                        detalle.setPrecioUnitario(BigDecimal.valueOf(producto.getPrecio()));
                    }

                    detalle.setProducto(producto);
                }

                // Calcular subtotal
                if (detalle.getPrecioUnitario() != null && detalle.getCantidad() != null) {
                    BigDecimal subtotal = detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad()));
                    detalle.setSubtotal(subtotal);
                    totalCalculado = totalCalculado.add(subtotal);
                } else if (detalle.getSubtotal() != null) {
                    totalCalculado = totalCalculado.add(detalle.getSubtotal());
                }
            }
        }

        if (venta.getTotal() == null || venta.getTotal().compareTo(BigDecimal.ZERO) == 0) {
            venta.setTotal(totalCalculado);
        }

        return ventaRepository.save(venta);
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
