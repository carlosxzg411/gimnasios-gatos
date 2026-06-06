package com.gimnasios.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String direccion;
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Role rol = Role.USER;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
