package com.gimnasios.product;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private String tamano;

    @Column(nullable = false)
    private String color;

    private String material;

    @Column(nullable = false)
    @Builder.Default
    private Integer stock = 0;

    private String imagenUrl;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
