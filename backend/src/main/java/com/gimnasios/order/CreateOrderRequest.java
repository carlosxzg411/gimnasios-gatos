package com.gimnasios.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotBlank
    private String direccionEnvio;
    private String metodoPago;
    @NotEmpty
    private List<ItemRequest> items;

    @Data
    public static class ItemRequest {
        private Long productId;
        private Integer cantidad;
        private String personalizacionTexto;
    }
}
