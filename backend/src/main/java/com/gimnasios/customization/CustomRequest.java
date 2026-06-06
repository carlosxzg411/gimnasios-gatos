package com.gimnasios.customization;

import com.gimnasios.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "custom_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    private String productoInteres;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CustomRequestStatus estado = CustomRequestStatus.PENDIENTE;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
