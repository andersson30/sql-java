# API de Gestión de Comerciantes

Este proyecto proporciona una API REST para la gestión de comerciantes y establecimientos, con funcionalidades de autenticación y autorización basadas en JWT.

## Requisitos Previos

- Java 17 o superior
- Maven
- Base de datos Oracle

## Configuración

1. Configurar las variables de entorno en `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
jwt.secret=tu_clave_secreta_jwt
```

## Endpoints de la API

### Autenticación

#### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "correo_electronico": "admin@empresa.com",
    "contrasena": "Admin123"
  }'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer"
}
```

### Comerciantes

#### Exportar Comerciantes Activos (CSV)
```bash
curl -X GET http://localhost:8080/api/comerciantes/exportar-activos \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Accept: text/csv" \
  --output comerciantes_activos.csv
```

El archivo CSV generado tendrá el siguiente formato:
```
Nombre o Razón Social|Municipio|Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Ingresos|Cantidad de Empleados
```

#### Obtener Todos los Comerciantes
```bash
curl -X GET http://localhost:8080/api/comerciantes \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Obtener Comerciante por ID
```bash
curl -X GET http://localhost:8080/api/comerciantes/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Crear Nuevo Comerciante
```bash
curl -X POST http://localhost:8080/api/comerciantes \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "nombre_razon_social": "Nuevo Comerciante S.A.",
    "municipio": "Bogotá",
    "telefono": "3111111111",
    "correo_electronico": "nuevo@empresa.com",
    "estado": "Activo"
  }'
```

#### Actualizar Comerciante
```bash
curl -X PUT http://localhost:8080/api/comerciantes/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "nombre_razon_social": "Comerciante Actualizado S.A.",
    "municipio": "Medellín",
    "telefono": "3222222222",
    "correo_electronico": "actualizado@empresa.com",
    "estado": "Activo"
  }'
```

#### Eliminar Comerciante
```bash
curl -X DELETE http://localhost:8080/api/comerciantes/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Establecimientos

#### Obtener Todos los Establecimientos
```bash
curl -X GET http://localhost:8080/api/establecimientos \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Obtener Establecimiento por ID
```bash
curl -X GET http://localhost:8080/api/establecimientos/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Crear Nuevo Establecimiento
```bash
curl -X POST http://localhost:8080/api/establecimientos \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Nuevo Establecimiento",
    "ingresos": 1000000.00,
    "numero_empleados": 10,
    "id_comerciante": 1
  }'
```

#### Actualizar Establecimiento
```bash
curl -X PUT http://localhost:8080/api/establecimientos/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Establecimiento Actualizado",
    "ingresos": 1500000.00,
    "numero_empleados": 15,
    "id_comerciante": 1
  }'
```

#### Eliminar Establecimiento
```bash
curl -X DELETE http://localhost:8080/api/establecimientos/{id} \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## Roles y Permisos

- **Administrador**: Acceso completo a todos los endpoints
- **Auxiliar de Registro**: Acceso limitado a operaciones básicas

## Notas Importantes

1. Todos los endpoints (excepto login) requieren autenticación mediante token JWT
2. El token debe incluirse en el header `Authorization` con el formato `Bearer {token}`
3. El endpoint de exportación de comerciantes activos solo está disponible para usuarios con rol "Administrador"
4. Los archivos CSV se generan con separador pipe (|)
5. Las fechas se muestran en formato dd/MM/yyyy

## Manejo de Errores

La API utiliza códigos de estado HTTP estándar:
- 200: OK
- 201: Created
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error

## Ejecución del Proyecto

1. Clonar el repositorio
2. Configurar las variables de entorno
3. Ejecutar con Maven:
```bash
mvn spring-boot:run
```

El servidor se iniciará en `http://localhost:8080` 