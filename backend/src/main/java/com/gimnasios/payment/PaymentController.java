package com.gimnasios.payment;

import com.gimnasios.order.Order;
import com.gimnasios.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody Map<String, Object> request) {
        Long orderId = Long.valueOf(request.get("orderId").toString());
        String paymentIntentId = paymentService.createPaymentIntent(orderId);
        return ResponseEntity.ok(Map.of("clientSecret", paymentIntentId));
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                 @RequestHeader("Stripe-Signature") String sigHeader) {
        paymentService.handleWebhook(payload, sigHeader);
        return ResponseEntity.ok("OK");
    }
}
