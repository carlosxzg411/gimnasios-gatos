package com.gimnasios.config;

import com.gimnasios.product.Product;
import com.gimnasios.product.ProductRepository;
import com.gimnasios.user.Role;
import com.gimnasios.user.User;
import com.gimnasios.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .nombre("Admin")
                    .email("admin@gimnasios.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Role.ADMIN)
                    .build());
        }
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Torre Clásica")
                    .descripcion("Torre de 3 niveles con rascador, plataforma y casita.")
                    .precio(new BigDecimal("89.99"))
                    .tamano("Grande")
                    .color("Beige")
                    .material("Sisal y MDF")
                    .stock(10)
                    .imagenUrl("https://placehold.co/400x400?text=Torre+Clasica")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Compacto")
                    .descripcion("Ideal para espacios pequeños. 2 niveles con juguete colgante.")
                    .precio(new BigDecimal("49.99"))
                    .tamano("Pequeño")
                    .color("Gris")
                    .material("Cartón y Sisal")
                    .stock(15)
                    .imagenUrl("https://placehold.co/400x400?text=Compacto")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Deluxe")
                    .descripcion("5 niveles con hamaca, rascadores y escondite.")
                    .precio(new BigDecimal("149.99"))
                    .tamano("Grande")
                    .color("Crema")
                    .material("Madera y Sisal")
                    .stock(5)
                    .imagenUrl("https://placehold.co/400x400?text=Deluxe")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Rascador Simple")
                    .descripcion("Poste rascador vertical con base estable.")
                    .precio(new BigDecimal("29.99"))
                    .tamano("Mediano")
                    .color("Natural")
                    .material("Sisal")
                    .stock(20)
                    .imagenUrl("https://placehold.co/400x400?text=Rascador")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Modular")
                    .descripcion("Módulos apilables para armar a tu gusto.")
                    .precio(new BigDecimal("119.99"))
                    .tamano("Grande")
                    .color("Blanco")
                    .material("MDF forrado en tela")
                    .stock(8)
                    .imagenUrl("https://placehold.co/400x400?text=Modular")
                    .build());
        }
    }
}
