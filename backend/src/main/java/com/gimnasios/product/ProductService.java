package com.gimnasios.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllActive() {
        return productRepository.findByActivoTrue();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Product> filtrar(String tamano, String color, String material,
                                  BigDecimal precioMin, BigDecimal precioMax) {
        return productRepository.filtrar(tamano, color, material, precioMin, precioMax);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product p = getById(id);
        p.setNombre(updated.getNombre());
        p.setDescripcion(updated.getDescripcion());
        p.setPrecio(updated.getPrecio());
        p.setTamano(updated.getTamano());
        p.setColor(updated.getColor());
        p.setMaterial(updated.getMaterial());
        p.setStock(updated.getStock());
        p.setImagenUrl(updated.getImagenUrl());
        p.setActivo(updated.getActivo());
        return productRepository.save(p);
    }

    public void delete(Long id) {
        Product p = getById(id);
        p.setActivo(false);
        productRepository.save(p);
    }
}
