-- Tabla USUARIO
CREATE TABLE USUARIO (
    id_usuario NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    correo_electronico VARCHAR2(100) NOT NULL UNIQUE,
    contrasena VARCHAR2(100) NOT NULL,
    rol VARCHAR2(20) NOT NULL CHECK (rol IN ('Administrador', 'Auxiliar de Registro'))
);

-- Tabla COMERCIANTE
CREATE TABLE COMERCIANTE (
    id_comerciante NUMBER PRIMARY KEY,
    nombre_razon_social VARCHAR2(200) NOT NULL,
    municipio VARCHAR2(100) NOT NULL,
    telefono VARCHAR2(20),
    correo_electronico VARCHAR2(100),
    fecha_registro DATE NOT NULL,
    estado VARCHAR2(10) NOT NULL CHECK (estado IN ('Activo', 'Inactivo')),
    fecha_actualizacion DATE,
    usuario_actualizacion VARCHAR2(100)
);

-- Tabla ESTABLECIMIENTO
CREATE TABLE ESTABLECIMIENTO (
    id_establecimiento NUMBER PRIMARY KEY,
    nombre VARCHAR2(200) NOT NULL,
    ingresos NUMBER(15,2) NOT NULL,
    numero_empleados NUMBER NOT NULL,
    id_comerciante NUMBER NOT NULL,
    fecha_actualizacion DATE,
    usuario_actualizacion VARCHAR2(100),
    CONSTRAINT fk_comerciante FOREIGN KEY (id_comerciante) REFERENCES COMERCIANTE(id_comerciante)
);