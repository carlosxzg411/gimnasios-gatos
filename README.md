# Gimnasios para Gatos

Sistema de venta de gimnasios, rascadores y accesorios para gatos. Catálogo interactivo, carrito de compras, pedidos personalizados y panel de administración.

## Stack Tecnológico

| Capa       | Tecnología                        |
| ---------- | --------------------------------- |
| Frontend   | Angular 22 · TypeScript 6 · RxJS  |
| Backend    | Spring Boot 3.4 · Java 21         |
| Base datos | MySQL 8 (prod) · H2 (dev)         |
| Pagos      | Stripe Payment Intents            |
| Auth       | JWT (jjwt 0.12)                   |
| Email      | Spring Mail + Thymeleaf templates |
| API Docs   | SpringDoc OpenAPI (Swagger UI)    |

## Requisitos

- **Java 21** (JDK)
- **Node.js 22** + npm
- **Maven 3.9**
- **Docker + Docker Compose** (para despliegue)
- Cuenta en **Stripe** (pagos)
- Cuenta SMTP (Gmail, SendGrid, etc.)

## Desarrollo local

### 1. Backend

```bash
cd backend
./mvnw spring-boot:run
# o si no tienes mvnw:
mvn spring-boot:run
```

El perfil activo por defecto es `dev` (base de datos H2 en memoria, `show-sql` activo).
La API corre en `http://localhost:8080`.
Swagger UI: `http://localhost:8080/swagger-ui.html`

### 2. Frontend

```bash
cd frontend
npm install
npm start
```

El proxy de desarrollo redirige `/api/*` a `http://localhost:8080`.
Abrir `http://localhost:4200`.

### 3. Usuario administrador (dev)

Al iniciar el backend con el perfil `dev`, se crea automáticamente:

| Email                | Password  | Rol    |
| -------------------- | --------- | ------ |
| `admin@gimnasios.com` | `admin123` | ADMIN  |

## Despliegue con Docker

### 1. Configurar variables de entorno

```bash
cp .env.example .env
# Editar .env con tus credenciales reales
```

### 2. Construir y ejecutar

```bash
docker compose up --build -d
```

La aplicación queda disponible en `http://localhost:8080`.

### 3. Detener

```bash
docker compose down
```

Para eliminar también el volumen de la base de datos:

```bash
docker compose down -v
```

## Despliegue en la nube

### Opción A — Render (recomendado)

Render despliega directamente desde GitHub con soporte nativo para Docker.

1. Crear cuenta en [render.com](https://render.com)
2. Ir a **Dashboard > New + > Blueprint**
3. Conectar tu repositorio de GitHub
4. Render detecta automáticamente el `docker-compose.yml` y crea los servicios
5. Configurar las variables de entorno en el dashboard de Render
6. Obtienes automáticamente HTTPS y dominio `*.onrender.com`

### Opción B — Railway

1. Crear cuenta en [railway.app](https://railway.app)
2. **New Project > Deploy from GitHub repo**
3. Railway detecta el `Dockerfile` y despliega automáticamente
4. Agregar MySQL desde el marketplace de Railway
5. Configurar variables de entorno en el dashboard

### Opción C — VPS (DigitalOcean, AWS EC2, etc.)

```bash
# 1. SSH a tu servidor
# 2. Clonar el repositorio
git clone https://github.com/tu-usuario/gimnasios-gatos.git
cd gimnasios-gatos

# 3. Configurar variables
cp .env.example .env
nano .env

# 4. Ejecutar con Docker
docker compose up --build -d

# 5. Configurar Nginx como reverse proxy con SSL (Certbot)
```

## Variables de Entorno

| Variable                  | Obligatoria | Descripción                                  | Ejemplo                          |
| ------------------------- | ----------- | -------------------------------------------- | -------------------------------- |
| `SPRING_PROFILES_ACTIVE`  | Sí          | Perfil de Spring (`dev` o `prod`)             | `prod`                           |
| `APP_JWT_SECRET`          | Sí          | Clave secreta JWT (mín. 32 caracteres)       | `cambiar-por-clave-segura-...`   |
| `STRIPE_SECRET_KEY`       | Sí*         | Clave secreta de Stripe                       | `sk_live_abc123...`              |
| `STRIPE_WEBHOOK_SECRET`   | Sí*         | Firma del webhook de Stripe                   | `whsec_abc123...`                |
| `MYSQL_ROOT_PASSWORD`     | Sí          | Password root de MySQL                        | `root-seguro-123`                |
| `MYSQL_PASSWORD`          | Sí          | Password del usuario de la aplicación         | `app-seguro-456`                 |
| `MYSQL_USER`              | No          | Usuario de MySQL para la app (default: app)   | `gimnasios_app`                  |
| `SMTP_HOST`               | Sí*         | Servidor SMTP para envío de correos           | `smtp.gmail.com`                 |
| `SMTP_PORT`               | No          | Puerto SMTP (default: 587)                    | `587`                            |
| `SMTP_USERNAME`           | Sí*         | Usuario SMTP                                  | `tucorreo@gmail.com`             |
| `SMTP_PASSWORD`           | Sí*         | Contraseña SMTP (app password para Gmail)     | `abcd1234`                       |
| `EMAIL_FROM`              | Sí*         | Dirección "De" en los correos                 | `no-reply@tudominio.com`         |
| `EMAIL_FABRICANTE`        | Sí*         | Correo del fabricante para notificaciones     | `fabrica@tudominio.com`          |
| `PORT`                    | No          | Puerto del servidor (default: 8080)           | `8080`                           |

*Obligatoria solo si usas la funcionalidad correspondiente (pagos, email).

## Estructura del proyecto

```
gimnasios-gatos/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/gimnasios/
│   │   │   │   ├── admin/          # Panel de administración
│   │   │   │   ├── auth/           # Autenticación JWT
│   │   │   │   ├── common/         # Manejo global de errores
│   │   │   │   ├── config/         # Seguridad, CORS, JWT filter
│   │   │   │   ├── customization/  # Pedidos personalizados
│   │   │   │   ├── notification/   # Servicio de correos
│   │   │   │   ├── order/          # Órdenes de compra
│   │   │   │   ├── payment/        # Integración con Stripe
│   │   │   │   ├── product/        # Productos
│   │   │   │   └── user/           # Usuarios y roles
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   └── app/
│   │       ├── components/
│   │       │   ├── admin/          # Dashboard, productos, órdenes
│   │       │   ├── auth/           # Login, registro
│   │       │   ├── cart/           # Carrito de compras
│   │       │   ├── checkout/       # Finalizar compra
│   │       │   ├── customization/  # Solicitar gimnasio personalizado
│   │       │   ├── products/       # Listado y detalle
│   │       │   └── shared/         # Header, footer
│   │       ├── models/
│   │       └── services/
│   ├── angular.json
│   └── package.json
├── Dockerfile
├── docker-compose.yml
└── .env.example
```

## API REST

| Método | Endpoint                        | Auth   | Descripción                          |
| ------ | ------------------------------- | ------ | ------------------------------------ |
| POST   | `/api/auth/register`            | No     | Registrar nuevo usuario              |
| POST   | `/api/auth/login`               | No     | Iniciar sesión                       |
| GET    | `/api/products`                 | No     | Listar productos                     |
| GET    | `/api/products/{id}`            | No     | Detalle de producto                  |
| POST   | `/api/orders`                   | JWT    | Crear orden                          |
| GET    | `/api/orders`                   | JWT    | Órdenes del usuario                  |
| GET    | `/api/orders/{id}`              | JWT    | Detalle de orden                     |
| POST   | `/api/payments/create-intent`   | JWT    | Crear PaymentIntent (Stripe)         |
| POST   | `/api/payments/webhook`         | No*    | Webhook de Stripe                    |
| POST   | `/api/custom-requests`          | JWT    | Solicitar gimnasio personalizado     |
| GET    | `/api/admin/**`                 | ADMIN  | Gestión de productos, órdenes, etc.  |

*Validado por firma de Stripe.

Documentación completa en Swagger UI (dev): `/swagger-ui.html`

## Licencia

MIT
