package com.gimnasios.admin;

import com.gimnasios.customization.CustomRequest;
import com.gimnasios.customization.CustomRequestStatus;
import com.gimnasios.customization.CustomizationService;
import com.gimnasios.order.Order;
import com.gimnasios.order.OrderService;
import com.gimnasios.order.OrderStatus;
import com.gimnasios.product.Product;
import com.gimnasios.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CustomizationService customizationService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                    @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }

    @GetMapping("/custom-requests")
    public ResponseEntity<List<CustomRequest>> getAllCustomRequests() {
        return ResponseEntity.ok(customizationService.getAll());
    }

    @PutMapping("/custom-requests/{id}/status")
    public ResponseEntity<CustomRequest> updateCustomStatus(@PathVariable Long id,
                                                             @RequestParam CustomRequestStatus status) {
        return ResponseEntity.ok(customizationService.updateStatus(id, status));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                  @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
