package com.gimnasios.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
    List<Order> findAllByOrderByFechaDesc();
}
