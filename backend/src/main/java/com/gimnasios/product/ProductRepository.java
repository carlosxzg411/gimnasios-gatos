package com.gimnasios.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActivoTrue();

    @Query("SELECT p FROM Product p WHERE p.activo = true AND " +
           "(:tamano IS NULL OR p.tamano = :tamano) AND " +
           "(:color IS NULL OR p.color = :color) AND " +
           "(:material IS NULL OR p.material = :material) AND " +
           "(:precioMin IS NULL OR p.precio >= :precioMin) AND " +
           "(:precioMax IS NULL OR p.precio <= :precioMax)")
    List<Product> filtrar(@Param("tamano") String tamano,
                          @Param("color") String color,
                          @Param("material") String material,
                          @Param("precioMin") java.math.BigDecimal precioMin,
                          @Param("precioMax") java.math.BigDecimal precioMax);
}
