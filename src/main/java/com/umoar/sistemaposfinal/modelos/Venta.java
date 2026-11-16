package com.umoar.sistemaposfinal.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Builder.Default
    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta = LocalDateTime.now();
    
    private BigDecimal total;

    @Builder.Default
    @Column(length = 20)
    private String tipo = "COMPRA"; // "COMPRA" o "RENTA"

    @Column(name = "numero_factura", length = 50)
    private String numeroFactura;

    @Column(name = "dias_renta")
    private Integer diasRenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> detalles;
}
