package com.gimnasios.customization;

import com.gimnasios.user.User;
import com.gimnasios.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomizationService {

    private final CustomRequestRepository repository;
    private final UserRepository userRepository;

    public CustomRequest create(Long userId, CustomRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        request.setUsuario(user);
        request.setEstado(CustomRequestStatus.PENDIENTE);
        return repository.save(request);
    }

    public List<CustomRequest> getUserRequests(Long userId) {
        return repository.findByUsuarioIdOrderByFechaDesc(userId);
    }

    public List<CustomRequest> getAll() {
        return repository.findAllByOrderByFechaDesc();
    }

    public CustomRequest updateStatus(Long id, CustomRequestStatus status) {
        CustomRequest r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        r.setEstado(status);
        return repository.save(r);
    }
}
