package com.gimnasios.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Product>> filtrar(
            @RequestParam(required = false) String tamano,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {
        return ResponseEntity.ok(productService.filtrar(tamano, color, material, precioMin, precioMax));
    }
}
