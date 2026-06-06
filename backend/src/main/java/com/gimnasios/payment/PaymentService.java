package com.gimnasios.payment;

import com.gimnasios.notification.EmailService;
import com.gimnasios.order.Order;
import com.gimnasios.order.OrderService;
import com.gimnasios.order.OrderStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.net.Webhook;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${app.stripe.secret-key}")
    private String stripeSecretKey;

    @Value("${app.stripe.webhook-secret}")
    private String webhookSecret;

    private final OrderService orderService;
    private final EmailService emailService;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public String createPaymentIntent(Long orderId) {
        Order order = orderService.getById(orderId);
        try {
            PaymentIntent intent = PaymentIntent.create(
                    java.util.Map.of(
                            "amount", order.getTotal().multiply(java.math.BigDecimal.valueOf(100)).longValue(),
                            "currency", "usd",
                            "metadata", java.util.Map.of("orderId", orderId.toString())
                    )
            );
            return intent.getClientSecret();
        } catch (StripeException e) {
            throw new RuntimeException("Error al crear pago: " + e.getMessage());
        }
    }

    public void handleWebhook(String payload, String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            if ("payment_intent.succeeded".equals(event.getType())) {
                EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
                if (deserializer.getObject().isPresent()) {
                    PaymentIntent intent = (PaymentIntent) deserializer.getObject().get();
                    Long orderId = Long.valueOf(intent.getMetadata().get("orderId"));
                    Order order = orderService.updateStatus(orderId, OrderStatus.PAGADO);
                    emailService.sendInvoiceToCustomer(order);
                    emailService.sendNotificationToFabricante(order);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error en webhook: " + e.getMessage());
        }
    }
}
