package com.gimnasios.order;

import com.gimnasios.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody CreateOrderRequest request,
                                        @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.createOrder(user.getId(), request));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getUserOrders(user.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
}
