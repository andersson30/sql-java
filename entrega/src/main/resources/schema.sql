-- Drop the table if it exists
DROP TABLE USUARIO CASCADE CONSTRAINTS;

-- Create the sequence
CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;

-- Create the table
CREATE TABLE USUARIO (
    id_usuario NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    correo_electronico VARCHAR2(100) NOT NULL UNIQUE,
    contrasena VARCHAR2(100) NOT NULL,
    rol VARCHAR2(20) NOT NULL,
    CONSTRAINT chk_rol CHECK (rol IN ('ADMINISTRADOR', 'AUXILIAR'))
); 