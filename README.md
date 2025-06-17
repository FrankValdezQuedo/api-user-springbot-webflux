# 🧩 Reactive User API

API REST para la gestión de usuarios, desarrollada con una arquitectura moderna y escalable.

## 🚀 Tecnologías

- ✅ **Spring WebFlux** – Programación reactiva no bloqueante
- ✅ **PostgreSQL** – Base de datos relacional
- ✅ **Arquitectura hexagonal (Ports & Adapters)**
- ✅ **Java 17+**
- ✅ **Manejo global de errores**
- ✅ **Validaciones con `jakarta.validation`**
- ✅ **Lombok** – Menos código repetitivo


> Arquitectura Hexagonal: separa el dominio de los detalles de infraestructura para mejorar testabilidad y mantenibilidad.

## 🧪 Cómo ejecutar

1. Clona el repositorio  
   `git clone https://github.com/tu-usuario/nombre-repo.git`

2. Configura tu base de datos PostgreSQL (puedes usar Docker):

   ```bash
   docker run --name postgres-user-api -e POSTGRES_DB=user_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres



