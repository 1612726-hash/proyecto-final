package com.umoar.sistemaposfinal.modelos;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer existencia;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Métodos de compatibilidad con los servicios existentes
    public Long getId() {
        return idProducto != null ? idProducto.longValue() : null;
    }

    public void setId(Long id) {
        this.idProducto = id != null ? id.intValue() : null;
    }

    public String getCodigoBarras() {
        return this.codigo;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigo = codigoBarras;
    }

    public Integer getStock() {
        return this.existencia;
    }

    public void setStock(Integer stock) {
        this.existencia = stock;
    }

    public BigDecimal getPrecioUnitarioBigDecimal() {
        return precio != null ? BigDecimal.valueOf(precio) : BigDecimal.ZERO;
    }
}
