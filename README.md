# API de Gestión de Comerciantes

Esta API proporciona endpoints para la gestión de comerciantes y establecimientos, incluyendo autenticación JWT y exportación de datos.

## Requisitos Previos

- Java 17 o superior
- Maven
- Base de datos Oracle con las tablas y funciones necesarias configuradas

## Configuración

1. Configurar las variables de entorno en `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

2. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

## Endpoints

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
  --output comerciantes_activos.csv
```

El archivo CSV generado tendrá el siguiente formato:
```
Nombre o Razón Social|Municipio|Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Ingresos|Cantidad de Empleados
```

Notas:
- Este endpoint requiere autenticación JWT
- Solo usuarios con rol "Administrador" pueden acceder
- El archivo se descarga con el nombre "comerciantes_activos.csv"
- Los campos están separados por el carácter pipe (|)
- La fecha se muestra en formato dd/MM/yyyy

## Roles y Permisos

- **Administrador**: Acceso completo a todos los endpoints
- **Auxiliar de Registro**: Acceso limitado a ciertos endpoints

## Manejo de Errores

La API utiliza códigos de estado HTTP estándar:
- 200: Éxito
- 400: Error en la solicitud
- 401: No autorizado
- 403: Acceso denegado
- 500: Error interno del servidor

## Ejemplos de Uso

1. Autenticarse como administrador:
```bash
# Obtener token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "correo_electronico": "admin@empresa.com",
    "contrasena": "Admin123"
  }' | jq -r '.token')

# Exportar CSV usando el token
curl -X GET http://localhost:8080/api/comerciantes/exportar-activos \
  -H "Authorization: Bearer $TOKEN" \
  --output comerciantes_activos.csv
```

## Notas Importantes

1. El token JWT debe incluirse en el header `Authorization` con el prefijo "Bearer"
2. El token expira después de un tiempo determinado
3. Para exportar el CSV, asegúrese de tener suficiente espacio en disco
4. Los datos exportados son de solo lectura y no pueden ser modificados 