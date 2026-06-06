package com.gimnasios.customization;

import com.gimnasios.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-requests")
@RequiredArgsConstructor
public class CustomizationController {

    private final CustomizationService customizationService;

    @PostMapping
    public ResponseEntity<CustomRequest> create(@Valid @RequestBody CustomRequest request,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(customizationService.create(user.getId(), request));
    }

    @GetMapping
    public ResponseEntity<List<CustomRequest>> getMyRequests(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(customizationService.getUserRequests(user.getId()));
    }
}
