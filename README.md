# API de Gestión de Alumnos

API REST completa desarrollada con Spring Boot para la gestión de estudiantes. Incluye operaciones CRUD, autenticación básica, validaciones, búsqueda avanzada, paginación y documentación interactiva con Swagger.

## Características Principales

- **CRUD Completo**: Crear, leer, actualizar y eliminar alumnos
- **Persistencia**: Base de datos relacional H2 con JPA/Hibernate
- **Seguridad**: Autenticación HTTP Basic con Spring Security
- **Validaciones**: Validación automática de datos con Bean Validation
- **Documentación**: Swagger UI interactiva (OpenAPI 3.0)
- **Búsqueda**: Filtrado de alumnos por nombre
- **Paginación**: Soporte para paginación y ordenamiento de resultados
- **Manejo de Errores**: Respuestas HTTP apropiadas con mensajes descriptivos

## Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|-----------|---------|-------------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 4.0.3 | Framework principal |
| Spring Data JPA | 4.0.3 | Persistencia de datos |
| Spring Security | 4.0.3 | Autenticación y autorización |
| H2 Database | Runtime | Base de datos en memoria/archivo |
| Hibernate Validator | 4.0.3 | Validación de datos |
| SpringDoc OpenAPI | 2.3.0 | Documentación Swagger |
| Lombok | Latest | Reducción de código boilerplate |
| Maven | 3.8+ | Gestión de dependencias |

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- **Java JDK 17 o superior**: [Descargar aquí](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8 o superior**: [Descargar aquí](https://maven.apache.org/download.cgi)
- **Git**: Para clonar el repositorio

Verifica las instalaciones:
```bash
java -version
mvn -version
```

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd ProyectoFinal
```

### 2. Compilar el Proyecto

```bash
mvn clean install
```

### 3. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

O usando el JAR generado:
```bash
java -jar target/ProyectoFinal-0.0.1-SNAPSHOT.jar
```

La aplicación se iniciará en: **http://localhost:8080**

## Configuración de Base de Datos

La aplicación usa **H2 Database** configurada para persistir datos en archivo:

- **URL de conexión**: `jdbc:h2:file:./data/alumnosdb`
- **Usuario**: `admin`
- **Contraseña**: `admin`
- **Ubicación de datos**: `./data/alumnosdb.mv.db`

### Acceso a H2 Console

Para inspeccionar la base de datos directamente:

1. Navega a: **http://localhost:8080/h2-console**
2. Ingresa las credenciales:
   - JDBC URL: `jdbc:h2:file:./data/alumnosdb`
   - Username: `admin`
   - Password: `admin`
3. Click en "Connect"

## Autenticación

Todos los endpoints de la API (`/api/**`) están protegidos con **HTTP Basic Authentication**.

### Credenciales de Acceso

- **Usuario**: `admin`
- **Contraseña**: `password123`

### Cómo autenticarse

#### En Swagger UI:
1. Click en el botón "Authorize" (candado verde)
2. Ingresa: `admin` / `password123`
3. Click en "Authorize" y luego "Close"

#### Con curl:
```bash
curl -u admin:password123 http://localhost:8080/api/alumnos
```

#### Con Postman:
1. Ve a la pestaña "Authorization"
2. Selecciona "Basic Auth"
3. Ingresa username: `admin`, password: `password123`

## Documentación de la API

### Swagger UI

Accede a la documentación interactiva en:

**http://localhost:8080/swagger-ui.html**

Swagger UI te permite:
- Ver todos los endpoints disponibles
- Probar las peticiones directamente desde el navegador
- Ver ejemplos de request y response
- Entender los parámetros requeridos

### Endpoints Disponibles

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/alumnos` | Obtener todos los alumnos | Requerida |
| GET | `/api/alumnos/paginated` | Obtener alumnos paginados | Requerida |
| GET | `/api/alumnos/{id}` | Obtener alumno por ID | Requerida |
| GET | `/api/alumnos/search?nombre={nombre}` | Buscar alumnos por nombre | Requerida |
| POST | `/api/alumnos` | Crear nuevo alumno | Requerida |
| PUT | `/api/alumnos/{id}` | Actualizar alumno existente | Requerida |
| DELETE | `/api/alumnos/{id}` | Eliminar alumno | Requerida |

## Ejemplos de Uso

### 1. Crear un Nuevo Alumno (POST)

**Request:**
```bash
curl -X POST http://localhost:8080/api/alumnos \
  -u admin:password123 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan.perez@ejemplo.com"
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan.perez@ejemplo.com",
  "fechaRegistro": "2026-02-20"
}
```

### 2. Obtener Todos los Alumnos (GET)

**Request:**
```bash
curl -X GET http://localhost:8080/api/alumnos \
  -u admin:password123
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan.perez@ejemplo.com",
    "fechaRegistro": "2026-02-20"
  },
  {
    "id": 2,
    "nombre": "María García",
    "email": "maria.garcia@ejemplo.com",
    "fechaRegistro": "2026-02-20"
  }
]
```

### 3. Obtener Alumno por ID (GET)

**Request:**
```bash
curl -X GET http://localhost:8080/api/alumnos/1 \
  -u admin:password123
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan.perez@ejemplo.com",
  "fechaRegistro": "2026-02-20"
}
```

### 4. Buscar Alumnos por Nombre (GET)

**Request:**
```bash
curl -X GET "http://localhost:8080/api/alumnos/search?nombre=juan" \
  -u admin:password123
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan.perez@ejemplo.com",
    "fechaRegistro": "2026-02-20"
  }
]
```

### 5. Obtener Alumnos con Paginación (GET)

**Request:**
```bash
curl -X GET "http://localhost:8080/api/alumnos/paginated?page=0&size=5&sort=nombre,asc" \
  -u admin:password123
```

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "email": "juan.perez@ejemplo.com",
      "fechaRegistro": "2026-02-20"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true
}
```

### 6. Actualizar un Alumno (PUT)

**Request:**
```bash
curl -X PUT http://localhost:8080/api/alumnos/1 \
  -u admin:password123 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos Pérez",
    "email": "juancarlos.perez@ejemplo.com"
  }'
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Juan Carlos Pérez",
  "email": "juancarlos.perez@ejemplo.com",
  "fechaRegistro": "2026-02-20"
}
```

### 7. Eliminar un Alumno (DELETE)

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/alumnos/1 \
  -u admin:password123
```

**Response (204 No Content):**
Sin cuerpo de respuesta.

## Validaciones

La aplicación valida automáticamente los datos de entrada:

| Campo | Validaciones |
|-------|-------------|
| nombre | Obligatorio (`@NotBlank`) |
| email | Obligatorio (`@NotBlank`) y formato válido (`@Email`) |
| fechaRegistro | Generada automáticamente, no modificable |

### Ejemplo de Error de Validación

**Request con datos inválidos:**
```bash
curl -X POST http://localhost:8080/api/alumnos \
  -u admin:password123 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "",
    "email": "email-invalido"
  }'
```

**Response (400 Bad Request):**
```json
{
  "timestamp": "2026-02-20T10:30:00",
  "status": 400,
  "error": "Error de validación",
  "message": "Los datos proporcionados no son válidos",
  "validationErrors": {
    "nombre": "El nombre es obligatorio",
    "email": "El email debe tener un formato válido"
  }
}
```

## Manejo de Errores

La API proporciona respuestas HTTP apropiadas para diferentes situaciones:

| Código HTTP | Situación | Ejemplo |
|-------------|-----------|---------|
| 200 OK | Operación exitosa | GET, PUT exitosos |
| 201 Created | Recurso creado | POST exitoso |
| 204 No Content | Eliminación exitosa | DELETE exitoso |
| 400 Bad Request | Datos inválidos | Validación fallida |
| 401 Unauthorized | Sin autenticación | Credenciales faltantes/incorrectas |
| 404 Not Found | Recurso no encontrado | Alumno no existe |
| 500 Internal Server Error | Error del servidor | Error inesperado |

## Estructura del Proyecto

```
ProyectoFinal/
├── src/
│   ├── main/
│   │   ├── java/com/example/ProyectoFinal/
│   │   │   ├── ProyectoFinalApplication.java   # Clase principal
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java          # Configuración de seguridad
│   │   │   │   └── OpenApiConfig.java           # Configuración de Swagger
│   │   │   ├── controller/
│   │   │   │   └── AlumnoController.java        # Controlador REST
│   │   │   ├── entity/
│   │   │   │   └── Alumno.java                  # Entidad JPA
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java  # Manejo global de errores
│   │   │   ├── repository/
│   │   │   │   └── AlumnoRepository.java        # Repositorio JPA
│   │   │   └── service/
│   │   │       └── AlumnoService.java           # Lógica de negocio
│   │   └── resources/
│   │       └── application.properties           # Configuración de la app
│   └── test/
├── data/                                        # Base de datos H2 (generada)
├── pom.xml                                      # Dependencias Maven
└── README.md                                    # Este archivo
```

## Características Bonus Implementadas

### 1. Paginación y Ordenamiento

Endpoint: `/api/alumnos/paginated`

Parámetros disponibles:
- `page`: Número de página (inicia en 0)
- `size`: Cantidad de elementos por página
- `sort`: Campo y dirección de ordenamiento (ej: `nombre,asc`)

Ejemplo:
```bash
GET /api/alumnos/paginated?page=0&size=10&sort=nombre,desc
```

### 2. Búsqueda por Nombre

Endpoint: `/api/alumnos/search?nombre={texto}`

Características:
- Búsqueda parcial (LIKE)
- No distingue mayúsculas/minúsculas
- Retorna todos los alumnos que contengan el texto

Ejemplo:
```bash
GET /api/alumnos/search?nombre=juan
```

### 3. Manejo Global de Excepciones

Todas las excepciones son capturadas y procesadas para retornar:
- Mensajes de error descriptivos
- Códigos HTTP apropiados
- Formato JSON consistente
- Timestamp del error

## Testing

### Pruebas Manuales con Swagger UI

1. Navega a http://localhost:8080/swagger-ui.html
2. Haz click en "Authorize" e ingresa las credenciales
3. Expande cada endpoint y prueba con el botón "Try it out"
4. Verifica las respuestas y códigos HTTP

### Pruebas con Postman

Importa esta colección básica:

1. Crea una nueva colección llamada "Alumnos API"
2. Configura Authorization (Basic Auth) a nivel de colección
3. Añade los endpoints descritos anteriormente
4. Ejecuta las peticiones en orden: POST → GET → PUT → DELETE

## Solución de Problemas

### La aplicación no inicia

**Problema**: Error de puerto en uso
```
Port 8080 is already in use
```

**Solución**: Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

### Error de autenticación

**Problema**: 401 Unauthorized

**Solución**: Verifica que estés enviando las credenciales correctas:
- Usuario: `admin`
- Contraseña: `password123`

### Base de datos no guarda los cambios

**Problema**: Los datos se pierden al reiniciar

**Solución**: Verifica que la URL de H2 sea de tipo archivo:
```properties
spring.datasource.url=jdbc:h2:file:./data/alumnosdb
```

## Próximos Pasos y Mejoras

- [ ] Migrar a MySQL/PostgreSQL para producción
- [ ] Implementar JWT en lugar de Basic Auth
- [ ] Añadir más campos a la entidad Alumno (teléfono, dirección, etc.)
- [ ] Implementar soft delete
- [ ] Añadir tests unitarios y de integración
- [ ] Implementar caché con Redis
- [ ] Crear frontend con React/Angular
- [ ] Desplegar en cloud (Heroku, AWS, Azure)

## Contribuciones

Para contribuir al proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia Apache 2.0 - ver el archivo LICENSE para más detalles.

## Contacto

Para preguntas o sugerencias, contacta a: desarrollo@ejemplo.com

---

**Desarrollado con Spring Boot 4.0.3 | Java 17 | Maven**
