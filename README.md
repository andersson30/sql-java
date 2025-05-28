# Sistema de Gestión de Entregas

## Descripción
Sistema de gestión de entregas desarrollado con Spring Boot y Oracle Database. El sistema permite la gestión de entregas, usuarios y roles con diferentes niveles de acceso.

## Tecnologías Utilizadas
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Oracle Database
- JWT para autenticación
- Swagger/OpenAPI para documentación
- Maven para gestión de dependencias

## Requisitos Previos
- Java 17 o superior
- Oracle Database (XE o superior)
- Maven
- IDE compatible con Java (recomendado: IntelliJ IDEA o Eclipse)

## Configuración de la Base de Datos
La aplicación está configurada para conectarse a una base de datos Oracle:
- Host: localhost
- Puerto: 49161
- SID: xe
- Usuario: system
- Contraseña: oracle

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── entrega/
│   │           └── entrega/
│   │               ├── application/
│   │               │   ├── dto/
│   │               │   ├── mapper/
│   │               │   └── service/
│   │               ├── domain/
│   │               │   ├── entity/
│   │               │   └── repository/
│   │               ├── infrastructure/
│   │               │   ├── config/
│   │               │   └── persistence/
│   │               └── presentation/
│   │                   └── controller/
│   └── resources/
│       ├── application.properties
│       ├── schema.sql
│       └── data.sql
```

## Configuración de la Aplicación
La aplicación se ejecuta en el puerto 8081 por defecto. La configuración principal se encuentra en `application.properties`:

### Configuración de la Base de Datos
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:49161:xe
spring.datasource.username=system
spring.datasource.password=oracle
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

### Configuración de JPA
```properties
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
```

### Configuración de JWT
```properties
jwt.secret=your_jwt_secret_key_here_make_it_long_and_secure_in_production
jwt.expiration=3600000
```

## Usuarios por Defecto
La aplicación viene con dos usuarios predefinidos:

1. Administrador:
   - Email: admin@empresa.com
   - Contraseña: password
   - Rol: ADMINISTRADOR

2. Auxiliar:
   - Email: auxiliar1@empresa.com
   - Contraseña: password
   - Rol: AUXILIAR

## Endpoints de la API
La documentación completa de la API está disponible a través de Swagger UI:
- URL: http://localhost:8081/swagger-ui.html

### Endpoints Principales
- `/api/auth/login` - Autenticación de usuarios
- `/api/entregas` - Gestión de entregas
- `/api/usuarios` - Gestión de usuarios

## Seguridad
- Autenticación basada en JWT
- Roles de usuario: ADMINISTRADOR y AUXILIAR
- Contraseñas hasheadas con BCrypt
- Validación de tokens JWT en cada petición

## Base de Datos
### Tabla USUARIO
```sql
CREATE TABLE USUARIO (
    id_usuario NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    correo_electronico VARCHAR2(100) NOT NULL UNIQUE,
    contrasena VARCHAR2(100) NOT NULL,
    rol VARCHAR2(20) NOT NULL,
    CONSTRAINT chk_rol CHECK (rol IN ('ADMINISTRADOR', 'AUXILIAR'))
);
```

## Ejecución del Proyecto
1. Clonar el repositorio
2. Configurar la base de datos Oracle
3. Ejecutar `mvn clean install`
4. Ejecutar `mvn spring-boot:run`
5. Acceder a http://localhost:8081

## Logging
La aplicación está configurada con diferentes niveles de logging:
- Spring Security: DEBUG
- Hibernate SQL: DEBUG
- Aplicación: DEBUG
- Connection Pool: DEBUG

## Consideraciones de Seguridad
- Las contraseñas se almacenan hasheadas con BCrypt
- Se utiliza JWT para la autenticación
- Los roles están estrictamente definidos
- Las credenciales de la base de datos deben ser cambiadas en producción

## Soporte
Para reportar problemas o solicitar ayuda, por favor crear un issue en el repositorio del proyecto. 