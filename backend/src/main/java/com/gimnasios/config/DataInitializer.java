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
            productRepository.save(Product.builder()
                    .nombre("Cama Colgante Ventana")
                    .descripcion("Cama tipo hamaca con ventosas ultra fuertes. Tu gato amará tomar el sol.")
                    .precio(new BigDecimal("39.99"))
                    .tamano("Mediano")
                    .color("Gris")
                    .material("Tela y ventosas")
                    .stock(12)
                    .imagenUrl("https://placehold.co/400x400?text=Cama+Colgante")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Túnel Plegable")
                    .descripcion("Túnel de juegos plegable con crinkle y juguete colgante.")
                    .precio(new BigDecimal("24.99"))
                    .tamano("Grande")
                    .color("Azul")
                    .material("Poliéster y alambre")
                    .stock(25)
                    .imagenUrl("https://placehold.co/400x400?text=Tunel")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Rascador Pared")
                    .descripcion("Rascador mural con diseño de olas. Ahorra espacio y decora.")
                    .precio(new BigDecimal("34.99"))
                    .tamano("Mediano")
                    .color("Beige")
                    .material("Sisal natural")
                    .stock(18)
                    .imagenUrl("https://placehold.co/400x400?text=Rascador+Pared")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Castillo Felino")
                    .descripcion("Castillo de 6 niveles con puente colgante, hamaca y cueva.")
                    .precio(new BigDecimal("199.99"))
                    .tamano("Grande")
                    .color("Crema")
                    .material("Madera y Sisal")
                    .stock(3)
                    .imagenUrl("https://placehold.co/400x400?text=Castillo")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Escalera")
                    .descripcion("Escalera de 4 peldaños forrados en sisal con plataforma superior.")
                    .precio(new BigDecimal("69.99"))
                    .tamano("Mediano")
                    .color("Natural")
                    .material("Madera y Sisal")
                    .stock(10)
                    .imagenUrl("https://placehold.co/400x400?text=Escalera")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Rascador Tipo Cactus")
                    .descripcion("Divertido poste rascador con forma de cactus. ¡Tu gato lo amará!")
                    .precio(new BigDecimal("44.99"))
                    .tamano("Mediano")
                    .color("Verde")
                    .material("Sisal y felpa")
                    .stock(14)
                    .imagenUrl("https://placehold.co/400x400?text=Cactus")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Cueva Igloo")
                    .descripcion("Cama tipo iglú con cojín removible. Sensación de seguridad y calidez.")
                    .precio(new BigDecimal("54.99"))
                    .tamano("Mediano")
                    .color("Negro")
                    .material("Felpa antialérgica")
                    .stock(9)
                    .imagenUrl("https://placehold.co/400x400?text=Igloo")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Kit Juguetes 10pz")
                    .descripcion("Set de 10 juguetes variados: pelotas, ratones, plumas y más.")
                    .precio(new BigDecimal("19.99"))
                    .tamano("Pequeño")
                    .color("Multicolor")
                    .material("Varios")
                    .stock(30)
                    .imagenUrl("https://placehold.co/400x400?text=Kit+Juguetes")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Fuente de Agua")
                    .descripcion("Fuente de agua con filtro de carbón. 3 modos de flujo. 2L de capacidad.")
                    .precio(new BigDecimal("45.99"))
                    .tamano("Pequeño")
                    .color("Blanco")
                    .material("Plástico libre de BPA")
                    .stock(20)
                    .imagenUrl("https://placehold.co/400x400?text=Fuente+Agua")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Árbol Rascador XL")
                    .descripcion("Árbol de 7 niveles con múltiples plataformas, casitas y rascadores.")
                    .precio(new BigDecimal("249.99"))
                    .tamano("Grande")
                    .color("Beige")
                    .material("Madera, Sisal y felpa")
                    .stock(4)
                    .imagenUrl("https://placehold.co/400x400?text=Arbol+XL")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Cama Gato Frutal")
                    .descripcion("Cama con forma de rodaja de sandía. Suave y lavable.")
                    .precio(new BigDecimal("32.99"))
                    .tamano("Pequeño")
                    .color("Verde")
                    .material("Felpa hipoalergénica")
                    .stock(16)
                    .imagenUrl("https://placehold.co/400x400?text=Cama+Frutal")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Estante Gato Ventana")
                    .descripcion("Estante con ventosa para que tu gato observe desde lo alto.")
                    .precio(new BigDecimal("28.99"))
                    .tamano("Pequeño")
                    .color("Negro")
                    .material("Metal y espuma")
                    .stock(22)
                    .imagenUrl("https://placehold.co/400x400?text=Estante")
                    .build());
            productRepository.save(Product.builder()
                    .nombre("Gimnasio Rascador Torre")
                    .descripcion("Torre compacta de 2 niveles con rascador de sisal y juguete.")
                    .precio(new BigDecimal("59.99"))
                    .tamano("Mediano")
                    .color("Crema")
                    .material("MDF y Sisal")
                    .stock(11)
                    .imagenUrl("https://placehold.co/400x400?text=Torre+2Niveles")
                    .build());
        }
    }
}
