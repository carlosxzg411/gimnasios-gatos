package com.gimnasios.auth;

import com.gimnasios.user.Role;
import com.gimnasios.user.User;
import com.gimnasios.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        User user = User.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .rol(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail(), user.getRol().name());
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .rol(user.getRol().name())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        String token = jwtService.generateToken(user.getEmail(), user.getRol().name());
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .rol(user.getRol().name())
                .build();
    }
}
