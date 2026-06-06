package com.gimnasios.customization;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomRequestRepository extends JpaRepository<CustomRequest, Long> {
    List<CustomRequest> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
    List<CustomRequest> findAllByOrderByFechaDesc();
}
