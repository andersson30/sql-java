-- Primero, verificar los valores actuales
SELECT DISTINCT rol FROM USUARIO;

-- Eliminar el constraint existente
ALTER TABLE USUARIO DROP CONSTRAINT SYS_C006991;

-- Crear el nuevo constraint con los valores correctos
ALTER TABLE USUARIO ADD CONSTRAINT SYS_C006991 CHECK (rol IN ('ADMINISTRADOR', 'AUXILIAR'));

-- Actualizar los roles existentes para que coincidan con los valores permitidos
UPDATE USUARIO SET rol = 'ADMINISTRADOR' WHERE LOWER(rol) = 'administrador';
UPDATE USUARIO SET rol = 'AUXILIAR' WHERE LOWER(rol) = 'auxiliar';

-- Actualizar la contraseña del administrador con BCrypt
UPDATE USUARIO 
SET contrasena = '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW',
    rol = 'ADMINISTRADOR'
WHERE correo_electronico = 'admin@empresa.com';

-- Actualizar la contraseña del auxiliar con BCrypt
UPDATE USUARIO 
SET contrasena = '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW',
    rol = 'AUXILIAR'
WHERE correo_electronico = 'auxiliar1@empresa.com';

-- Delete existing users with these email addresses
DELETE FROM USUARIO WHERE correo_electronico IN ('admin@empresa.com', 'auxiliar1@empresa.com');

-- Insert admin user
INSERT INTO USUARIO (id_usuario, nombre, correo_electronico, contrasena, rol) 
VALUES (seq_usuario.NEXTVAL, 'Admin Principal', 'admin@empresa.com', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'ADMINISTRADOR');

-- Insert auxiliar user
INSERT INTO USUARIO (id_usuario, nombre, correo_electronico, contrasena, rol) 
VALUES (seq_usuario.NEXTVAL, 'Auxiliar Principal', 'auxiliar1@empresa.com', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'AUXILIAR'); 