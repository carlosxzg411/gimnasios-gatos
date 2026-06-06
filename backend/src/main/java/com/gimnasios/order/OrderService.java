package com.gimnasios.order;

import com.gimnasios.product.Product;
import com.gimnasios.product.ProductRepository;
import com.gimnasios.user.User;
import com.gimnasios.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUsuarioIdOrderByFechaDesc(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByFechaDesc();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    @Transactional
    public Order createOrder(Long userId, CreateOrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<OrderItem> items = request.getItems().stream()
                .map(itemReq -> {
                    Product product = productRepository.findById(itemReq.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                    if (product.getStock() < itemReq.getCantidad()) {
                        throw new RuntimeException("Stock insuficiente para: " + product.getNombre());
                    }
                    product.setStock(product.getStock() - itemReq.getCantidad());
                    productRepository.save(product);
                    return OrderItem.builder()
                            .product(product)
                            .cantidad(itemReq.getCantidad())
                            .precioUnitario(product.getPrecio())
                            .personalizacionTexto(itemReq.getPersonalizacionTexto())
                            .build();
                })
                .collect(Collectors.toList());
        BigDecimal total = items.stream()
                .map(i -> i.getPrecioUnitario().multiply(BigDecimal.valueOf(i.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = Order.builder()
                .usuario(user)
                .fecha(LocalDateTime.now())
                .total(total)
                .direccionEnvio(request.getDireccionEnvio())
                .metodoPago(request.getMetodoPago())
                .items(items)
                .build();
        items.forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order order = getById(id);
        order.setEstado(status);
        return orderRepository.save(order);
    }
}
