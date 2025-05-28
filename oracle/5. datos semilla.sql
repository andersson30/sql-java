-- Usuarios
INSERT INTO USUARIO (nombre, correo_electronico, contrasena, rol) 
VALUES ('Admin Principal', 'admin@empresa.com', 'Admin123', 'Administrador');

INSERT INTO USUARIO (nombre, correo_electronico, contrasena, rol) 
VALUES ('Auxiliar 1', 'auxiliar1@empresa.com', 'Auxiliar123', 'Auxiliar de Registro');

-- Comerciantes
INSERT INTO COMERCIANTE (nombre_razon_social, municipio, telefono, correo_electronico, fecha_registro, estado)
VALUES ('Comerciante Uno S.A.', 'Bogotá', '3111111111', 'comerciante1@empresa.com', TO_DATE('01/01/2023', 'DD/MM/YYYY'), 'Activo');

INSERT INTO COMERCIANTE (nombre_razon_social, municipio, telefono, correo_electronico, fecha_registro, estado)
VALUES ('Tiendas El Buen Precio', 'Medellín', '3222222222', 'comerciante2@empresa.com', TO_DATE('15/02/2023', 'DD/MM/YYYY'), 'Activo');

INSERT INTO COMERCIANTE (nombre_razon_social, municipio, telefono, correo_electronico, fecha_registro, estado)
VALUES ('Distribuidora La Economía', 'Cali', NULL, NULL, TO_DATE('10/03/2023', 'DD/MM/YYYY'), 'Activo');

INSERT INTO COMERCIANTE (nombre_razon_social, municipio, telefono, correo_electronico, fecha_registro, estado)
VALUES ('Supermercados Del Norte', 'Barranquilla', '3333333333', 'comerciante4@empresa.com', TO_DATE('05/04/2023', 'DD/MM/YYYY'), 'Inactivo');

INSERT INTO COMERCIANTE (nombre_razon_social, municipio, telefono, correo_electronico, fecha_registro, estado)
VALUES ('Almacenes El Ahorro', 'Cartagena', '3444444444', 'comerciante5@empresa.com', TO_DATE('20/05/2023', 'DD/MM/YYYY'), 'Activo');

-- Establecimientos (asignación aleatoria a comerciantes)
INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Tienda Principal', 15000000.50, 8, 1);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal Norte', 8500000.75, 5, 1);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('El Buen Precio Centro', 12000000.00, 10, 2);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('El Buen Precio Occidente', 9500000.25, 7, 2);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Bodega Principal', 5000000.00, 3, 3);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Centro de Distribución', 7500000.00, 6, 3);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Super Del Norte Sede 1', 6000000.50, 4, 4);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Almacén Ahorro Centro', 11000000.00, 9, 5);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Almacén Ahorro Norte', 8000000.00, 6, 5);

INSERT INTO ESTABLECIMIENTO (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Almacén Ahorro Sur', 6500000.00, 5, 5);

COMMIT;