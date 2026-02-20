# Guía Rápida de Inicio

## Ejecutar el Proyecto en 3 Pasos

### Paso 1: Verificar Java 17

```bash
java -version
```

Si no tienes Java 17, descárgalo de: https://adoptium.net/

### Paso 2: Compilar y Ejecutar

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Paso 3: Probar la API

Abre tu navegador en: **http://localhost:8080/swagger-ui.html**

**Credenciales:**
- Usuario: `admin`
- Contraseña: `password123`

---

## Endpoints Rápidos

### Crear Alumno (POST)
```bash
curl -X POST http://localhost:8080/api/alumnos \
  -u admin:password123 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Pérez","email":"juan@ejemplo.com"}'
```

### Listar Alumnos (GET)
```bash
curl -u admin:password123 http://localhost:8080/api/alumnos
```

### Buscar por Nombre (GET)
```bash
curl -u admin:password123 "http://localhost:8080/api/alumnos/search?nombre=juan"
```

---

## URLs Importantes

| Recurso | URL |
|---------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| API REST | http://localhost:8080/api/alumnos |
| H2 Console | http://localhost:8080/h2-console |

---

## H2 Console

**Acceso:** http://localhost:8080/h2-console

**Configuración:**
- JDBC URL: `jdbc:h2:file:./data/alumnosdb`
- Username: `admin`
- Password: `admin`

---

## Solución de Problemas

### Puerto en uso
Cambia el puerto en `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Java no encontrado
Asegúrate de que JAVA_HOME esté configurado:
```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17
```

---

Para más información, consulta el **README.md** completo.
